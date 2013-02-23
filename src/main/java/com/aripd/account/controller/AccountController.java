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

import com.aripd.account.domain.Account;
import com.aripd.account.service.IAccountService;
import com.aripd.account.service.IRoleService;
import com.aripd.account.validator.AccountValidator;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/account")
public class AccountController {

	protected static Logger logger = Logger.getLogger(AccountController.class);

	@Resource(name = "accountService")
	private IAccountService accountService;

	@Resource(name = "roleService")
	private IRoleService roleService;

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
		logger.debug("Received request to show existing record");
		model.addAttribute("accountAttribute", accountService.findOne(id));
		return "account/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add new record");
		model.addAttribute("accountAttribute", new Account());
		return "account/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("accountAttribute", accountService.findOne(id));
		return "account/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("accountAttribute") @Valid Account account,
			BindingResult result) {
		if (result.hasErrors()) {
			logger.error(result);
			return "/account/form";
		}

		logger.debug("Received request to save existing record");
		accountService.save(account);
		return "redirect:/account/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		accountService.delete(id);
		return "redirect:/account/list";
	}

}
