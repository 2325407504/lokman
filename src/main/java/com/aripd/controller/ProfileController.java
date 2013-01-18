package com.aripd.controller;

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

import com.aripd.service.RoleService;
import com.aripd.service.UserService;

@Controller
@PreAuthorize("isFullyAuthenticated()")
@RequestMapping("/profile")
public class ProfileController {
	
	protected static Logger logger4J = Logger.getLogger(ProfileController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showAction(Model model) {
		logger4J.debug("Received request to show add new record");
		model.addAttribute("userAttribute", userService.getActiveUser());
		return "profile/show";
	}

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editAction(Model model) {
		User securityUser = (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		
		com.aripd.domain.User user = userService.getOneByUsername(securityUser.getUsername());
		
		logger4J.debug("Received request to show edit existing record");
    	model.addAttribute("userAttribute", user);
    	return "profile/form";
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("userAttribute") @Valid com.aripd.domain.User user, BindingResult result) {
		if (result.hasErrors()) {
			logger4J.error(result);
			return "/profile/form";
		}
		
		logger4J.debug("Received request to save existing record");
		userService.save(user);
		return "redirect:/profile/list";
	}
	
}
