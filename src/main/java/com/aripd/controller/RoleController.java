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

import com.aripd.domain.Role;
import com.aripd.service.RoleService;
import com.aripd.validator.RoleValidator;

@Controller
@RequestMapping("/role")
public class RoleController {

	protected static Logger logger4J = Logger.getLogger(RoleController.class);

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "roleValidator")
	private RoleValidator roleValidator;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		if (logger4J.isDebugEnabled()) {
			logger4J.debug("Received request to show all records");
		}
		model.addAttribute("roleAttribute", roleService.getAll());
		return "role/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger4J.debug("Received request to show add new record");
		model.addAttribute("roleAttribute", new Role());
		return "role/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger4J.debug("Received request to show edit existing record");
		model.addAttribute("roleAttribute", roleService.getOne(id));
		return "role/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(@ModelAttribute("roleAttribute") Role role, Errors errors) {
		roleValidator.validate(role, errors);
		if (errors.hasErrors()) {
			return "/role/form";
		}
		
		logger4J.debug("Received request to save existing record");
		roleService.save(role);
		return "redirect:/role/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger4J.debug("Received request to delete existing record");
		roleService.delete(id);
		return "redirect:/role/list";
	}

}
