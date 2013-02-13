package com.aripd.project.lokman.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.service.DriverService;
import com.aripd.project.lokman.service.TripService;
import com.aripd.project.lokman.service.TruckService;
import com.aripd.project.lokman.validator.TripValidator;

@Controller
@RequestMapping("/trip")
public class TripController {

	protected static Logger logger = Logger.getLogger(TripController.class);

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

	@Secured("ROLE_USER")
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

	@Secured("ROLE_USER")
	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("tripAttribute", tripService.getAll());
		return "trip/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show existing record");
		model.addAttribute("tripAttribute", tripService.getOne(id));
		return "trip/show";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add page");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("tripAttribute", new Trip());
		return "trip/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("tripAttribute", tripService.getOne(id));
		return "trip/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("tripAttribute")/* @Valid */Trip formData,
			BindingResult result, Model model, Principal principal) {
		tripValidator.validate(formData, result);
		if (result.hasErrors()) {
			logger.error(result);
			model.addAttribute("trucks", truckService.getAll());
			model.addAttribute("drivers", driverService.getAll());
			return "/trip/form";
		}

		logger.debug("Received request to save existing record");

		Account account = accountService.getOneByUsername(principal.getName());
		formData.setAccount(account);

		tripService.save(formData);
		//tripService.saveOrUpdate(formData);
		return "redirect:/trip/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		tripService.delete(id);
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
		logger.debug("Received request to export report as an XLS");
		tripService.exportXLS(response);
	}

}
