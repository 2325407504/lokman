package com.aripd.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aripd.domain.FMS;
import com.aripd.service.DriverService;
import com.aripd.service.FMSService;
import com.aripd.service.TruckService;

@Controller
@RequestMapping("/fms")
public class FMSController {
	
	protected static Logger logger4J = Logger.getLogger(FMSController.class);
	
	@Resource(name="fmsService")
	private FMSService fmsService;
	
	@Resource(name="truckService")
	private TruckService truckService;
	
	@Resource(name="driverService")
	private DriverService driverService;
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		if (logger4J.isDebugEnabled()) {
			logger4J.debug("Received request to show all records");
		}
		model.addAttribute("fmsAttribute", fmsService.getAll());
		return "fms/list";
	}

	@Secured("ROLE_USER")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger4J.debug("Received request to show add page");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
    	model.addAttribute("fmsAttribute", new FMS());
    	return "fms/form";
	}

	@Secured("ROLE_USER")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger4J.debug("Received request to show edit existing record");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
    	model.addAttribute("fmsAttribute", fmsService.getOne(id));
    	return "fms/form";
	}

	@Secured("ROLE_USER")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
    		@ModelAttribute("fmsAttribute") @Valid FMS formData, 
    		BindingResult result, 
    		Model model
    ) {
		if (result.hasErrors()) {
			logger4J.error(result);
			model.addAttribute("trucks", truckService.getAll());
			model.addAttribute("drivers", driverService.getAll());
			return "/fms/form";
		}
		
		logger4J.debug("Received request to save existing record");
		fmsService.save(formData);
		return "redirect:/fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger4J.debug("Received request to delete existing record");
		fmsService.delete(id);
		return "redirect:/fms/list";
	}

}
