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

import com.aripd.project.lgk.domain.Quota;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.validator.QuotaValidator;

@Controller
@RequestMapping("/quota")
public class QuotaController {
	
	protected static Logger logger = Logger.getLogger(QuotaController.class);
	
	@Resource(name="quotaService")
	private QuotaService quotaService;
	
	@Resource(name = "quotaValidator")
	private QuotaValidator quotaValidator;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("quotaAttribute", quotaService.findAll());
		return "quota/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger.debug("Received request to show add page");
    	model.addAttribute("quotaAttribute", new Quota());
    	return "quota/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
    	model.addAttribute("quotaAttribute", quotaService.findOne(id));
    	return "quota/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("quotaAttribute") Quota quota, Errors errors) {
		quotaValidator.validate(quota, errors);
		if (errors.hasErrors()) {
			return "/quota/form";
		}
		
		logger.debug("Received request to save existing record");
		quotaService.save(quota);
		return "redirect:/quota/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		quotaService.delete(id);
		return "redirect:/quota/list";
	}

}
