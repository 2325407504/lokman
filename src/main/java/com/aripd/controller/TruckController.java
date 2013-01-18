package com.aripd.controller;

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

import com.aripd.domain.Truck;
import com.aripd.service.TruckService;
import com.aripd.validator.TruckValidator;

@Controller
@RequestMapping("/truck")
public class TruckController {
	
	protected static Logger logger4J = Logger.getLogger(TruckController.class);
	
	@Resource(name="truckService")
	private TruckService truckService;
	
	@Resource(name = "truckValidator")
	private TruckValidator truckValidator;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		if (logger4J.isDebugEnabled()) {
			logger4J.debug("Received request to show all records");
		}    	
		model.addAttribute("truckAttribute", truckService.getAll());
		return "truck/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger4J.debug("Received request to show add page");
    	model.addAttribute("truckAttribute", new Truck());
    	return "truck/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger4J.debug("Received request to show edit existing record");
    	model.addAttribute("truckAttribute", truckService.getOne(id));
    	return "truck/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("truckAttribute") Truck truck, Errors errors) {
		truckValidator.validate(truck, errors);
		if (errors.hasErrors()) {
			return "/truck/form";
		}
		
		logger4J.debug("Received request to save existing record");
		truckService.save(truck);
		return "redirect:/truck/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String _____updateAction(@ModelAttribute("truckAttribute") Truck truck, 
    		@PathVariable Long id, Model model) {
		logger4J.debug("Received request to update person");
    
    	// The "personAttribute" model has been passed to the controller from the JSP
    	// We use the name "personAttribute" because the JSP uses that name
    	
    	// We manually assign the id because we disabled it in the JSP page
    	// When a field is disabled it will not be included in the ModelAttribute
    	truck.setId(id);
    	
    	// Delegate to PersonService for editing
    	// We show the all persons page again after updating the person
    	truckService.update(truck);
    	
    	// Retrieve all persons and attach to model
    	model.addAttribute("truckAttribute", truckService.getAll());
    	
		return "redirect:/truck/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger4J.debug("Received request to delete existing record");
		truckService.delete(id);
		return "redirect:/truck/list";
	}

}
