package com.aripd.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.aripd.domain.User;
import com.aripd.service.RoleService;
import com.aripd.service.UserService;
import com.aripd.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	protected static Logger logger4J = Logger.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name = "userValidator")
	private UserValidator userValidator;

	@Resource(name="roleService")
	private RoleService roleService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list")
	public String listAction(Model model) {
		logger4J.debug("Received request to show all persons page");

		model.addAttribute("entities", userService.getAll());
		
		return "user/list";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		logger4J.debug("Received request to show add page");
    
    	List<Role> roles = roleService.getAll();
    	Set set = new HashSet(roles);
    	
    	User user = new User();
    	user.setRoles(set);
    	model.addAttribute("user", user);

    	return "user/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		logger4J.debug("Received request to show edit existing record");
    	model.addAttribute("userAttribute", userService.get(id));
    	return "user/form";
	}

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(@ModelAttribute("userAttribute") User user, Errors errors) {
		userValidator.validate(user, errors);
		if (errors.hasErrors()) {
			return "/user/form";
		}
		
		logger4J.debug("Received request to save existing record");
		userService.save(user);
		return "redirect:/user/list";
	}
	
	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Long id, 
    										Model model) {
   
		logger4J.debug("Received request to delete existing person");
		
		// Call PersonService to do the actual deleting
		userService.delete(id);
		
		// Add id reference to Model
		model.addAttribute("id", id);
    	
		return "redirect:/user/list";
	}

}
