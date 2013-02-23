package com.aripd.account.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.account.domain.Role;
import com.aripd.account.service.IRoleService;
import com.aripd.account.validator.RoleValidator;

@Controller
@RequestMapping("/role")
public class RoleController {

	protected static Logger logger = Logger.getLogger(RoleController.class);

	@Resource(name = "roleService")
	private IRoleService roleService;

	@Resource(name = "roleValidator")
	private RoleValidator roleValidator;

	@Secured("ROLE_SUPERADMIN")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("roleAttribute", roleService.findAll());
		return "role/list";
	}

	@Secured("ROLE_SUPERADMIN")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add new record");
		model.addAttribute("roleAttribute", new Role());
		return "role/form";
	}

	@Secured("ROLE_SUPERADMIN")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("roleAttribute", roleService.findOne(id));
		return "role/form";
	}

	@Secured("ROLE_SUPERADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			final RedirectAttributes redirectAttributes,
			@ModelAttribute("roleAttribute") @Valid Role role, 
			BindingResult result
	) {
		if (result.hasErrors()) {
			logger.error(result);
			return "/role/form";
		}
		
		logger.debug("Received request to save existing record");
		roleService.save(role);
		redirectAttributes.addFlashAttribute("message", "Successfully saved..");
		return "redirect:/role/list";
	}

	@Secured("ROLE_SUPERADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id
	) {
		logger.debug("Received request to delete existing record");
		roleService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Successfully deleted..");
		return "redirect:/role/list";
	}

}
