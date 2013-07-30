package com.aripd.project.lgk.controller;

import com.aripd.member.domain.Member;
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
import com.aripd.project.lgk.domain.Employeeleave;
import com.aripd.project.lgk.model.EmployeeleaveFilterByIntervalForm;
import com.aripd.project.lgk.service.EmployeeleaveService;
import com.aripd.project.lgk.service.EmployeeleavetypeService;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/employeeleave")
public class EmployeeleaveController {

    @Resource(name = "employeeleaveService")
    private EmployeeleaveService employeeleaveService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "employeeleavetypeService")
    private EmployeeleavetypeService employeeleavetypeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Employeeleave> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Employeeleave> resultset = this.employeeleaveService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "employeeleave/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("employeeleaveAttribute", employeeleaveService.findOne(id));
        return "employeeleave/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("employeeleavetypes", employeeleavetypeService.findAll());
        model.addAttribute("employeeleaveAttribute", new Employeeleave());
        return "employeeleave/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("employeeleavetypes", employeeleavetypeService.findAll());
        model.addAttribute("employeeleaveAttribute", employeeleaveService.findOne(id));
        return "employeeleave/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeleaveAttribute") @Valid Employeeleave formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("employeeleavetypes", employeeleavetypeService.findAll());
            return "/employeeleave/form";
        }

        Employeeleave employeeleave = employeeleaveService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/employeeleave/show/" + employeeleave.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Employeeleave employeeleave = employeeleaveService.findOne(id);
        employeeleave.setSubmitted(true ^ employeeleave.isSubmitted());
        employeeleaveService.save(employeeleave);
        return "redirect:/employeeleave/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        employeeleaveService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/employeeleave/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("employeeleaveFilterByIntervalForm", new EmployeeleaveFilterByIntervalForm());
        return "employeeleave/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeleaveFilterByIntervalForm") @Valid EmployeeleaveFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            return "/employeeleave/report";
        }

        Member member = memberService.findOne(formData.getEmployee().getMember().getId());
        employeeleaveService.export(response, member);
        return "redirect:/employeeleave/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("employeeleaveImportAttribute", new FileUploadBean());
        return "employeeleave/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeleaveImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/employeeleave/import";
        }

        employeeleaveService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/employeeleave/list";
    }
}
