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

import com.aripd.account.domain.Role;
import com.aripd.account.dto.RoleDto;
import com.aripd.account.exception.RoleNotFoundException;
import com.aripd.account.service.RoleService;
import com.aripd.account.validator.RoleValidator;

@Controller
@RequestMapping("/role")
public class RoleController {

	protected static Logger logger = Logger.getLogger(RoleController.class);

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "roleValidator")
	private RoleValidator roleValidator;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("roleAttribute", roleService.getAll());
		return "role/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add new record");
		model.addAttribute("roleAttribute", new Role());
		return "role/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("roleAttribute", roleService.getOne(id));
		return "role/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(@ModelAttribute("roleAttribute") @Valid RoleDto formData, BindingResult result) throws RoleNotFoundException {
		if (result.hasErrors()) {
			logger.error(result);
			return "/role/form";
		}
		
		logger.debug("Received request to save existing record");
		roleService.save(formData);
		return "redirect:/role/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) throws RoleNotFoundException {
		logger.debug("Received request to delete existing record");
		roleService.delete(id);
		return "redirect:/role/list";
	}

}
