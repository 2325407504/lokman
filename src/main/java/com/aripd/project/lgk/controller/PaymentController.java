package com.aripd.project.lgk.controller;

import com.aripd.member.domain.Member;
import com.aripd.member.service.MemberService;
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

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Payment;
import com.aripd.project.lgk.model.PaymentFilterByIntervalForm;
import com.aripd.project.lgk.service.EmployeeService;
import com.aripd.project.lgk.service.PaymentService;
import com.aripd.project.lgk.service.PaymenttypeService;
import java.security.Principal;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Resource(name = "paymentService")
    private PaymentService paymentService;
    @Resource(name = "paymenttypeService")
    private PaymenttypeService paymenttypeService;
    @Resource(name = "employeeService")
    private EmployeeService employeeService;
    @Resource(name = "memberService")
    private MemberService memberService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Payment> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Payment> resultset = this.paymentService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/payment/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("paymentAttribute", paymentService.findOne(id));
        return "/payment/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("paymenttypes", paymenttypeService.findAll());
        model.addAttribute("paymentAttribute", new Payment());
        return "/payment/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("paymenttypes", paymenttypeService.findAll());
        model.addAttribute("paymentAttribute", paymentService.findOne(id));
        return "/payment/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("paymentAttribute") @Valid Payment formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("paymenttypes", paymenttypeService.findAll());
            return "/payment/form";
        }

        Member member = memberService.findOneByUsername(principal.getName());
        formData.setMember(member);

        Payment payment = paymentService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/payment/show/" + payment.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @RequestParam(value = "id", required = true) Long id) {
        
        paymentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/payment/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("paymentFilterByIntervalForm", new PaymentFilterByIntervalForm());
        return "/payment/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("paymentFilterByIntervalForm") @Valid PaymentFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "/payment/report";
        }

        paymentService.export(response, formData);
        return "redirect:/payment/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("paymentImportAttribute", new FileUploadBean());
        return "/payment/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("paymentImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/payment/import";
        }

        paymentService.importData(formData.getFile(), principal);
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/payment/list";
    }
}
