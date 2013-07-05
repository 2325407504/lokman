package com.aripd.project.lgk.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.model.ForwardingFilterByIntervalForm;
import com.aripd.project.lgk.model.UatfFilterByIntervalForm;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.SubcontractorService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/forwarding")
public class ForwardingController {

    @Resource(name = "forwardingService")
    private ForwardingService forwardingService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;
    @Resource(name = "subcontractorService")
    private SubcontractorService subcontractorService;
    @Resource(name = "accountService")
    private AccountService accountService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Forwarding> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Forwarding> resultset = this.forwardingService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "forwarding/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("forwardingAttribute", forwardingService.findOne(id));
        return "forwarding/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findAll());
        model.addAttribute("forwardingAttribute", new Forwarding());
        return "forwarding/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("uatfAttribute", new Uatf());
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findAll());
        model.addAttribute("forwardingAttribute", forwardingService.findOne(id));
        return "forwarding/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("forwardingAttribute") @Valid Forwarding formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("quotas", quotaService.findAll());
            model.addAttribute("subcontractors", subcontractorService.findAll());
            return "/forwarding/form";
        }

        Forwarding forwarding = forwardingService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/forwarding/show" + forwarding.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Forwarding forwarding = forwardingService.findOne(id);
        forwarding.setSubmitted(true ^ forwarding.isSubmitted());
        forwardingService.save(forwarding);
        return "redirect:/forwarding/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        forwardingService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/forwarding/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("forwardingFilterByIntervalForm", new ForwardingFilterByIntervalForm());
        model.addAttribute("uatfFilterByIntervalForm", new UatfFilterByIntervalForm());
        return "forwarding/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("forwardingFilterByIntervalForm") @Valid ForwardingFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/forwarding/report";
        }

        forwardingService.exportByInterval(response, startingTime, endingTime);
        return "redirect:/forwarding/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute(new FileUploadBean());
        return "forwarding/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importXLS(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("fileUploadBean") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/forwarding/import";
        }

        forwardingService.importXLS(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/forwarding/list";
    }
}
