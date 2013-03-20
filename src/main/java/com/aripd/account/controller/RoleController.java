package com.aripd.account.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.account.domain.Role;
import com.aripd.account.service.RoleService;
import com.aripd.account.validator.RoleValidator;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/role")
public class RoleController {

	protected static Logger logger = Logger.getLogger(RoleController.class);

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "roleValidator")
	private RoleValidator roleValidator;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Role> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Role> resultset = this.roleService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAction(Model model) {
		return "role/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show existing record");
		model.addAttribute("roleAttribute", roleService.findOne(id));
		return "role/show";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add new record");
		model.addAttribute("roleAttribute", new Role());
		return "role/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("roleAttribute", roleService.findOne(id));
		return "role/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(final RedirectAttributes redirectAttributes,
			@ModelAttribute("roleAttribute") @Valid Role role,
			BindingResult result) {
		if (result.hasErrors()) {
			logger.error(result);
			return "/role/form";
		}

		logger.debug("Received request to save existing record");
		roleService.save(role);
		redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		roleService.delete(id);
		redirectAttributes.addFlashAttribute("message",
				"Silme işlemi başarı ile tamamlandı");
		return "redirect:/role/list";
	}

}
