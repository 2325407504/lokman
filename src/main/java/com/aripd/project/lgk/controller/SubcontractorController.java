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

import com.aripd.project.lgk.domain.Subcontractor;
import com.aripd.project.lgk.service.SubcontractorService;
import com.aripd.project.lgk.validator.SubcontractorValidator;

@Controller
@RequestMapping("/subcontractor")
public class SubcontractorController {
	
	protected static Logger logger = Logger.getLogger(SubcontractorController.class);
	
	@Resource(name="subcontractorService")
	private SubcontractorService subcontractorService;
	
	@Resource(name = "subcontractorValidator")
	private SubcontractorValidator subcontractorValidator;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("subcontractorAttribute", subcontractorService.findAll());
		return "subcontractor/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger.debug("Received request to show add page");
    	model.addAttribute("subcontractorAttribute", new Subcontractor());
    	return "subcontractor/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
    	model.addAttribute("subcontractorAttribute", subcontractorService.findOne(id));
    	return "subcontractor/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("subcontractorAttribute") Subcontractor subcontractor, Errors errors) {
		subcontractorValidator.validate(subcontractor, errors);
		if (errors.hasErrors()) {
			return "/subcontractor/form";
		}
		
		logger.debug("Received request to save existing record");
		subcontractorService.save(subcontractor);
		return "redirect:/subcontractor/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		subcontractorService.delete(id);
		return "redirect:/subcontractor/list";
	}

}
