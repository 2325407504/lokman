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

import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.service.TruckService;
import com.aripd.project.lgk.validator.TruckValidator;

@Controller
@RequestMapping("/truck")
public class TruckController {
	
	protected static Logger logger = Logger.getLogger(TruckController.class);
	
	@Resource(name="truckService")
	private TruckService truckService;
	
	@Resource(name = "truckValidator")
	private TruckValidator truckValidator;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}    	
		model.addAttribute("truckAttribute", truckService.findAll());
		return "truck/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger.debug("Received request to show add page");
    	model.addAttribute("truckAttribute", new Truck());
    	return "truck/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
    	model.addAttribute("truckAttribute", truckService.findOne(id));
    	return "truck/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("truckAttribute") Truck truck, Errors errors) {
		truckValidator.validate(truck, errors);
		if (errors.hasErrors()) {
			return "/truck/form";
		}
		
		logger.debug("Received request to save existing record");
		truckService.save(truck);
		return "redirect:/truck/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		truckService.delete(id);
		return "redirect:/truck/list";
	}

}
