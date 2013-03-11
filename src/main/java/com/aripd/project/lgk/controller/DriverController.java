package com.aripd.project.lgk.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.validator.DriverValidator;

@Controller
@RequestMapping("/driver")
public class DriverController {
	
	protected static Logger logger = Logger.getLogger(DriverController.class);
	
	@Resource(name="driverService")
	private DriverService driverService;
	
	@Resource(name = "driverValidator")
	private DriverValidator driverValidator;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("driverAttribute", driverService.findAll());
		return "driver/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger.debug("Received request to show add page");
    	model.addAttribute("driverAttribute", new Driver());
    	return "driver/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
    	model.addAttribute("driverAttribute", driverService.findOne(id));
    	return "driver/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("driverAttribute") Driver driver, Errors errors) {
		driverValidator.validate(driver, errors);
		if (errors.hasErrors()) {
			return "/driver/form";
		}
		
		logger.debug("Received request to save existing record");
		driverService.save(driver);
		return "redirect:/driver/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		driverService.delete(id);
		return "redirect:/driver/list";
	}

}
