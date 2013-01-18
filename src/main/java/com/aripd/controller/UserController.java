package com.aripd.controller;

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

import com.aripd.domain.User;
import com.aripd.service.RoleService;
import com.aripd.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	protected static Logger logger4J = Logger.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		logger4J.debug("Received request to show all persons page");

		model.addAttribute("userAttribute", userService.getAll());
		
		return "user/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger4J.debug("Received request to show add new record");
		model.addAttribute("userAttribute", new User());
		return "user/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger4J.debug("Received request to show edit existing record");
    	model.addAttribute("userAttribute", userService.getOne(id));
    	return "user/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("userAttribute") @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			logger4J.error(result);
			return "/user/form";
		}
		
		logger4J.debug("Received request to save existing record");
		userService.save(user);
		return "redirect:/user/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger4J.debug("Received request to delete existing record");
		userService.delete(id);
		return "redirect:/user/list";
	}

}
