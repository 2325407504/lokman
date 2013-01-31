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

import com.aripd.account.domain.Account;
import com.aripd.account.dto.AccountDto;
import com.aripd.account.exception.AccountNotFoundException;
import com.aripd.account.service.AccountService;
import com.aripd.account.service.RoleService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	protected static Logger logger = Logger.getLogger(AccountController.class);
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		logger.debug("Received request to show records");
		model.addAttribute("accountAttribute", accountService.getAll());
		return "account/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show record");
    	model.addAttribute("account", accountService.getOne(id));
    	return "account/show";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add new record");
		model.addAttribute("accountAttribute", new Account());
		return "account/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
    	model.addAttribute("accountAttribute", accountService.getOne(id));
    	return "account/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("accountAttribute") @Valid AccountDto formData, BindingResult result) throws AccountNotFoundException {
		if (result.hasErrors()) {
			logger.error(result);
			return "/account/form";
		}
		
		logger.debug("Received request to save existing record");
		accountService.save(formData);
		return "redirect:/account/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) throws AccountNotFoundException {
		logger.debug("Received request to delete existing record");
		accountService.delete(id);
		return "redirect:/account/list";
	}

}
