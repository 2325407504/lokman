package com.aripd.account.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aripd.account.domain.Account;
import com.aripd.account.service.IAccountService;
import com.aripd.account.service.IRoleService;

@Controller
@PreAuthorize("isFullyAuthenticated()")
@RequestMapping("/profile")
public class ProfileController {
	
	protected static Logger logger = Logger.getLogger(ProfileController.class);
	
	@Resource(name="accountService")
	private IAccountService accountService;
	
	@Resource(name="roleService")
	private IRoleService roleService;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showAction(Model model) {
		logger.debug("Received request to show add new record");
		model.addAttribute("profileAttribute", accountService.getActiveUser());
		return "profile/show";
	}

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editAction(Model model) {
		User securityUser = (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		
		Account account = accountService.getOneByUsername(securityUser.getUsername());
		
		logger.debug("Received request to show edit existing record");
    	model.addAttribute("profileAttribute", account);
    	return "profile/form";
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("profileAttribute") @Valid Account account, BindingResult result) {
		if (result.hasErrors()) {
			logger.error(result);
			return "/profile/form";
		}
		
		logger.debug("Received request to save existing record");
		accountService.save(account);
		return "redirect:/profile/list";
	}
	
}
