package com.aripd.project.lokman.controller;

import java.security.Principal;

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
import com.aripd.account.service.AccountService;
import com.aripd.project.lokman.domain.Shift;
import com.aripd.project.lokman.service.ShiftService;

@Controller
@RequestMapping("/shift")
public class ShiftController {

	protected static Logger logger = Logger.getLogger(ShiftController.class);

	@Resource(name = "shiftService")
	private ShiftService shiftService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("shiftAttribute", shiftService.getAll());
		return "shift/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add page");
		model.addAttribute("shiftAttribute", new Shift());
		return "shift/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("shiftAttribute", shiftService.getOne(id));
		return "shift/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("shiftAttribute") @Valid Shift formData,
			BindingResult result, Model model, Principal principal) {
		if (result.hasErrors()) {
			logger.error(result);
			return "/shift/form";
		}

		logger.debug("Received request to save existing record");

		Account account = accountService.getOneByUsername(principal.getName());
		formData.setAccount(account);

		shiftService.save(formData);
		return "redirect:/shift/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		shiftService.delete(id);
		return "redirect:/shift/list";
	}

}
