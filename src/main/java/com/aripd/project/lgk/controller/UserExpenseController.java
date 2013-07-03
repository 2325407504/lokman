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
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.service.ExpenseService;
import com.aripd.project.lgk.service.ExpensetypeService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_USER') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/userexpense")
public class UserExpenseController {

    @Resource(name = "expenseService")
    private ExpenseService expenseService;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "expensetypeService")
    private ExpensetypeService expensetypeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Expense> datatablesAction(
            Principal principal,
            @DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Expense> resultset = this.expenseService.getRecords(principal, criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/userexpense/list";
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
            return "redirect:/userexpense/list";
        }

        model.addAttribute("userexpenseAttribute", expense);
        return "/userexpense/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("expensetypes", expensetypeService.findAll());
        model.addAttribute("userexpenseAttribute", new Expense());
        return "/userexpense/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            final RedirectAttributes redirectAttributes,
            @PathVariable Long id,
            Model model) {

        Expense expense = expenseService.findOne(id);
        if (expense.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/userexpense/show/" + expense.getId();
        }

        model.addAttribute("expensetypes", expensetypeService.findAll());
        model.addAttribute("userexpenseAttribute", expense);
        return "/userexpense/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("userexpenseAttribute") @Valid Expense formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/userexpense/form";
        }

        Account account = accountService.findOneByUsername(principal.getName());
        formData.setAccount(account);

        expenseService.save(formData);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/userexpense/list";
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
            return "redirect:/userexpense/list";
        }

        expense.setSubmitted(true);
        expenseService.save(expense);
        return "redirect:/userexpense/show/" + id;
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
            return "redirect:/userexpense/list";
        } else if (expense.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/userexpense/show/" + expense.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", "Kaydınız başarı ile silindi");
            expenseService.delete(expense);
            return "redirect:/userexpense/list";
        }
    }
}
