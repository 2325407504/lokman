package com.aripd.project.lgk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.service.ExpenseService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/expense")
public class ExpenseController {

	@Resource(name = "expenseService")
	private ExpenseService expenseService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Expense> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Expense> resultset = this.expenseService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		return "expense/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(
			@PathVariable Long id,
			Model model) {
		model.addAttribute("expenseAttribute", expenseService.findOne(id));
		return "expense/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("expenseAttribute", new Expense());
		return "expense/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(
			@PathVariable Long id, 
			Model model) {
		model.addAttribute("uatfAttribute", new Uatf());
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("expenseAttribute", expenseService.findOne(id));
		return "expense/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			final RedirectAttributes redirectAttributes,
			@ModelAttribute("expenseAttribute") @Valid Expense formData,
			BindingResult result, 
			Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("accounts", accountService.findAll());
			return "/expense/form";
		}

		expenseService.save(formData);
		redirectAttributes.addFlashAttribute("message", "Successfully saved..");
		return "redirect:/expense/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id) {
		expenseService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
		return "redirect:/expense/list";
	}

	@RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
	public String submitAction(@PathVariable Long id) {
		Expense expense = expenseService.findOne(id);
		expense.setSubmitted(true^expense.isSubmitted());
		expenseService.save(expense);
		return "redirect:/expense/show/"+id;
	}
	
	/**
	 * Exports the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 */
	@RequestMapping(value = "/export/xls", method = RequestMethod.GET)
	public void getXLS(HttpServletResponse response, Model model) {
		expenseService.exportXLS(response);
	}

}
