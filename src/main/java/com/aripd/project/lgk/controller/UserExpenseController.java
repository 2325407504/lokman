package com.aripd.project.lgk.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

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

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.service.ExpenseService;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/user/expense")
public class UserExpenseController {

	@Resource(name = "expenseService")
	private ExpenseService expenseService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Expense> getDataTables(
			Principal principal,
			@TableParam PagingCriteria criteria) {
		ResultSet<Expense> resultset = this.expenseService.getRecords(principal, criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		return "/user/expense/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(
			final RedirectAttributes redirectAttributes,
			Principal principal,
			@PathVariable Long id,
			Model model) {
		
		Account account = accountService.findOneByUsername(principal.getName());
		Expense expense = expenseService.findOneByAccountAndId(account, id);
		
		if (expense instanceof Expense == false) {
			redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
			return "redirect:/user/expense/list";
		}
		
		model.addAttribute("expenseAttribute", expense);
		return "/user/expense/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		model.addAttribute("expenseAttribute", new Expense());
		return "/user/expense/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(
			final RedirectAttributes redirectAttributes,
			@PathVariable Long id, 
			Model model) {
		
		Expense expense = expenseService.findOne(id);
		if (expense.isSubmitted()) {
			redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
			return "redirect:/user/expense/list";
		}
		
		model.addAttribute("expenseAttribute", expense);
		return "/user/expense/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			final RedirectAttributes redirectAttributes,
			Principal principal,
			@ModelAttribute("expenseAttribute") @Valid Expense formData,
			BindingResult result, 
			Model model) {
		
		if (result.hasErrors()) {
			return "/user/expense/form";
		}

		Account account = accountService.findOneByUsername(principal.getName());
		formData.setAccount(account);

		expenseService.save(formData);
		redirectAttributes.addFlashAttribute("message", "Successfully saved..");
		return "redirect:/user/expense/list";
	}

	@RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
	public String submitAction(
			final RedirectAttributes redirectAttributes,
			Principal principal,
			@PathVariable Long id, 
			Model model) {
		
		Account account = accountService.findOneByUsername(principal.getName());
		Expense expense = expenseService.findOneByAccountAndId(account, id);
		
		if (expense instanceof Expense == false) {
			redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
			return "redirect:/user/expense/list";
		}

		expense.setSubmitted(true);
		expenseService.save(expense);
		return "redirect:/user/expense/show/"+id;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			Principal principal,
			@RequestParam(value = "id", required = true) Long id) {
		
		Account account = accountService.findOneByUsername(principal.getName());
		Expense expense = expenseService.findOneByAccountAndId(account, id);
		
		if (expense instanceof Expense == false) {
			redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
		}
		else if (expense.isSubmitted()) {
			redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Kaydınız başarı ile silindi");
			expenseService.delete(expense);
		}
		
		return "redirect:/user/expense/list";
	}

}
