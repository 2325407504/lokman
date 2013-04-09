package com.aripd.account.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
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

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.account.service.RoleService;
import com.aripd.account.validator.AccountValidator;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.service.RegionService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/account")
public class AccountController {

	@Resource(name = "accountService")
	private AccountService accountService;

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name="regionService")
	private RegionService regionService;

	@Resource(name = "accountValidator")
	private AccountValidator accountValidator;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Account> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Account> resultset = this.accountService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAction(Model model) {
		return "account/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(@PathVariable Long id, Model model) {
		model.addAttribute("accountAttribute", accountService.findOne(id));
		return "account/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		model.addAttribute("regions", regionService.findAll());
		model.addAttribute("accountAttribute", new Account());
		return "account/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		model.addAttribute("regions", regionService.findAll());
		model.addAttribute("accountAttribute", accountService.findOne(id));
		return "account/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("accountAttribute") @Valid Account formData,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "/account/form";
		}

		if (formData.getId() != null) {
			Account account = accountService.findOne(formData.getId());
			if (formData.getPassword().length() == 0) {
				formData.setPassword(account.getPassword());
			}
			else {
				formData.setPassword(DigestUtils.md5Hex(formData.getPassword()));
			}
		}
		else {
			formData.setPassword(DigestUtils.md5Hex(formData.getPassword()));
		}
		
		accountService.save(formData);
		return "redirect:/account/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		accountService.delete(id);
		return "redirect:/account/list";
	}

}
