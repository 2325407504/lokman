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

import com.aripd.member.domain.Member;
import com.aripd.member.service.MemberService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.service.ExpenseService;
import com.aripd.project.lgk.service.ExpensetypeService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@Controller
@RequestMapping("/userexpense")
public class UserExpenseController {

    @Resource(name = "expenseService")
    private ExpenseService expenseService;
    @Resource(name = "memberService")
    private MemberService memberService;
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

        Member member = memberService.findOneByUsername(principal.getName());
        Expense expense = expenseService.findOneByMemberAndId(member, id);

        if (expense instanceof Expense == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
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
            redirectAttributes.addFlashAttribute("message", "message.record.not.editable");
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
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("expensetypes", expensetypeService.findAll());
            return "/userexpense/form";
        }

        Member member = memberService.findOneByUsername(principal.getName());
        formData.setMember(member);

        Expense expense = expenseService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/userexpense/show/" + expense.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Member member = memberService.findOneByUsername(principal.getName());
        Expense expense = expenseService.findOneByMemberAndId(member, id);

        if (expense instanceof Expense == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
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

        Member member = memberService.findOneByUsername(principal.getName());
        Expense expense = expenseService.findOneByMemberAndId(member, id);

        if (expense instanceof Expense == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/userexpense/list";
        } else if (expense.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.editable");
            return "redirect:/userexpense/show/" + expense.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", "message.completed.delete");
            expenseService.delete(expense);
            return "redirect:/userexpense/list";
        }
    }
}
