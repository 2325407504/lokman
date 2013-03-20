package com.aripd.project.lgk.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.CsvImportBean;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.TripService;
import com.aripd.project.lgk.service.TruckService;
import com.aripd.project.lgk.validator.TripValidator;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private TripValidator tripValidator;

	@Resource(name = "tripService")
	private TripService tripService;

	@Resource(name = "truckService")
	private TruckService truckService;

	@Resource(name = "driverService")
	private DriverService driverService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@RequestMapping(value = "/chart")
	public String chartAction(Model model) {
		return "trip/chart";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Trip> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Trip> resultset = this.tripService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		return "trip/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(
			@PathVariable Long id, 
			Model model) {
		model.addAttribute("tripAttribute", tripService.findOne(id));
		return "trip/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("trucks", truckService.findAll());
		model.addAttribute("drivers", driverService.findAll());
		model.addAttribute("tripAttribute", new Trip());
		return "trip/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(
			final RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			@PathVariable Long id, 
			Model model) {
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("trucks", truckService.findAll());
		model.addAttribute("drivers", driverService.findAll());
		model.addAttribute("tripAttribute", tripService.findOne(id));
		return "trip/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			final RedirectAttributes redirectAttributes,
			@ModelAttribute("tripAttribute")/* @Valid */Trip formData,
			BindingResult result, 
			Model model) {
		
		tripValidator.validate(formData, result);
		if (result.hasErrors()) {
			model.addAttribute("accounts", accountService.findAll());
			model.addAttribute("trucks", truckService.findAll());
			model.addAttribute("drivers", driverService.findAll());
			return "/trip/form";
		}

		tripService.save(formData);
		redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
		return "redirect:/trip/list";
	}

	@RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
	public String submitAction(@PathVariable Long id) {
		Trip trip = tripService.findOne(id);
		trip.setSubmitted(true^trip.isSubmitted());
		tripService.save(trip);
		return "redirect:/trip/show/"+id;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id) {
		tripService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
		return "redirect:/trip/list";
	}

	/**
	 * Exports the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 */
	@RequestMapping(value = "/export/xls", method = RequestMethod.GET)
	public void getXLS(HttpServletResponse response, Model model) {
		tripService.exportXLS(response);
	}

	@RequestMapping(value = "/import/xls", method = RequestMethod.GET)
	public String importAction(Model model) {
		model.addAttribute(new FileUploadBean());
		model.addAttribute(new CsvImportBean());
		return "trip/import";
	}

	@RequestMapping(value = "/import/xls", method = RequestMethod.POST)
	public String importXLS(
			final RedirectAttributes redirectAttributes,
			FileUploadBean fileUploadBean, 
			BindingResult result) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Hata oluştu");
			return "redirect:/trip/import";
		}

		String fileName = null;
		try {
			MultipartFile file = fileUploadBean.getFile();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				if (file.getSize() > 1000000) {
					redirectAttributes.addFlashAttribute("message", "Dosya boyutu büyük");
					return "redirect:/trip/import";
				}
				
				fileName = "/tmp/" + file.getOriginalFilename();
				
				outputStream = new FileOutputStream(fileName);

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tripService.importXLSX(fileName);
		redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
		return "redirect:/trip/list";
	}

	@RequestMapping(value = "/import/csv", method = RequestMethod.POST)
	public String importCSV(
			final RedirectAttributes redirectAttributes,
			CsvImportBean csvImportBean, 
			BindingResult result) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Hata oluştu");
			return "redirect:/trip/import";
		}
		
		tripService.importCSV(csvImportBean.getContent());
		redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
		return "redirect:/trip/list";
	}

}
