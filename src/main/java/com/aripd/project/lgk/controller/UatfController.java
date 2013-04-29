package com.aripd.project.lgk.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.SubcontractorService;
import com.aripd.project.lgk.service.UatfService;
import com.aripd.project.lgk.validator.UatfValidator;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/uatf")
public class UatfController {

	@Autowired
	private UatfValidator uatfValidator;

	@Resource(name = "forwardingService")
	private ForwardingService forwardingService;

	@Resource(name = "uatfService")
	private UatfService uatfService;

	@Resource(name = "quotaService")
	private QuotaService quotaService;

	@Resource(name = "subcontractorService")
	private SubcontractorService subcontractorService;

	@Resource(name = "driverService")
	private DriverService driverService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@RequestMapping(value = "/get/{forwarding_id}", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Uatf> getDataTables(@PathVariable Long forwarding_id, @TableParam PagingCriteria criteria) {
		ResultSet<Uatf> customers = this.uatfService.getRecords(forwarding_id, criteria);
		return ControllerUtils.getWebResultSet(criteria, customers);
	}
	
	@RequestMapping(value = "/save/{forwarding_id}", method = RequestMethod.POST)
	public String saveAction(
			final RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			@PathVariable Long forwarding_id, 
			@ModelAttribute("uatfAttribute")/* @Valid */Uatf formData,
			BindingResult result, 
			Model model) {

		Forwarding forwarding = forwardingService.findOne(forwarding_id);
		if (forwarding.isSubmitted() && request.isUserInRole("ROLE_USER")) {
			redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
			return "redirect:/forwarding/list";
		}

		uatfValidator.validate(formData, result);
		if (result.hasErrors()) {
			model.addAttribute("drivers", driverService.findAll());
			return "/uatf/form";
		}

		formData.setForwarding(forwarding);

		uatfService.save(formData);
		return "redirect:/forwarding/edit/"+forwarding_id;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(
			final RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			@PathVariable Long id) {
		
		Uatf uatf = uatfService.findOne(id);
		
		if (uatf.getForwarding().isSubmitted() && request.isUserInRole("ROLE_USER")) {
			redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
			return "redirect:/forwarding/list";
		}

		uatfService.delete(uatf);
		return "redirect:/forwarding/edit/"+uatf.getForwarding().getId();
	}

	/**
	 * Exports the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 */
	@RequestMapping(value = "/export/xls", method = RequestMethod.GET)
	public void getXLS(HttpServletResponse response, Model model) {
		uatfService.exportXLS(response);
	}

	@Value("${path.directory.import}")
	String pathDirectoryImport;

	@RequestMapping(value = "/import/xls", method = RequestMethod.POST)
	public String importXLS(
			final RedirectAttributes redirectAttributes,
			FileUploadBean fileUploadBean, 
			BindingResult result) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Hata oluştu");
			return "redirect:/forwarding/import";
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
					return "redirect:/forwarding/import";
				}
				
				fileName = pathDirectoryImport + file.getOriginalFilename();
				
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

		uatfService.importXLSX(fileName);
		redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
		return "redirect:/forwarding/list";
	}

}
