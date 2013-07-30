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

import com.aripd.member.service.MemberService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.model.ExpenseFilterByIntervalForm;
import com.aripd.project.lgk.service.ExpenseService;
import com.aripd.project.lgk.service.ExpensetypeService;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Resource(name = "expenseService")
    private ExpenseService expenseService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "expensetypeService")
    private ExpensetypeService expensetypeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Expense> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Expense> resultset = this.expenseService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/expense/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("expenseAttribute", expenseService.findOne(id));
        return "/expense/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("expensetypes", expensetypeService.findAll());
        model.addAttribute("expenseAttribute", new Expense());
        return "/expense/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("expensetypes", expensetypeService.findAll());
        model.addAttribute("expenseAttribute", expenseService.findOne(id));
        return "/expense/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("expenseAttribute") @Valid Expense formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("expensetypes", expensetypeService.findAll());
            return "/expense/form";
        }

        Expense expense = expenseService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/expense/show/" + expense.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Expense expense = expenseService.findOne(id);
        expense.setSubmitted(true ^ expense.isSubmitted());
        expenseService.save(expense);
        return "redirect:/expense/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        expenseService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/expense/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("expenseFilterByIntervalForm", new ExpenseFilterByIntervalForm());
        return "/expense/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("expenseFilterByIntervalForm") @Valid ExpenseFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            return "/expense/report";
        }

        expenseService.export(response, formData.getStartingDate(), formData.getEndingDate(), formData.getEmployee().getMember().getId());
        return "redirect:/expense/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("expenseImportAttribute", new FileUploadBean());
        return "/expense/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("expenseImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/expense/import";
        }

        expenseService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/expense/list";
    }
}
