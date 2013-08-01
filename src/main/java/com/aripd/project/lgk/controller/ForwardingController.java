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

import com.aripd.member.service.MemberService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.model.ForwardingFilterByIntervalForm;
import com.aripd.project.lgk.service.EndingpointService;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.StartingpointService;
import com.aripd.project.lgk.service.SubcontractorService;
import com.aripd.project.lgk.service.TruckService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/forwarding")
public class ForwardingController {

    @Autowired
    private MessageSource messageSource;
    @Resource(name = "forwardingService")
    private ForwardingService forwardingService;
    @Resource(name = "truckService")
    private TruckService truckService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;
    @Resource(name = "subcontractorService")
    private SubcontractorService subcontractorService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "startingpointService")
    private StartingpointService startingpointService;
    @Resource(name = "endingpointService")
    private EndingpointService endingpointService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Forwarding> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Forwarding> resultset = this.forwardingService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/forwarding/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("forwardingAttribute", forwardingService.findOne(id));
        return "/forwarding/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findAll());
        model.addAttribute("startingpoints", startingpointService.findAll());
        model.addAttribute("endingpoints", endingpointService.findAll());
        model.addAttribute("forwardingAttribute", new Forwarding());
        return "/forwarding/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("uatfAttribute", new Uatf());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findAll());
        model.addAttribute("startingpoints", startingpointService.findAll());
        model.addAttribute("endingpoints", endingpointService.findAll());
        model.addAttribute("forwardingAttribute", forwardingService.findOne(id));
        return "/forwarding/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("forwardingAttribute") @Valid Forwarding formData,
            BindingResult result,
            Model model) {

        String message = messageSource.getMessage("message.duplicated.waybillNo", null, LocaleContextHolder.getLocale());
        if (formData.getId() == null) {
            Forwarding check1 = forwardingService.findOneByWaybillNo(formData.getWaybillNo());
            if (check1 != null) {
                result.addError(new ObjectError("waybillNo", message));
            }
        } else {
            boolean check2 = forwardingService.isExistByWaybillNoExceptId(formData.getWaybillNo(), formData.getId());
            if (check2) {
                result.addError(new ObjectError("waybillNo", message));
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("uatfAttribute", new Uatf());
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("quotas", quotaService.findAll());
            model.addAttribute("subcontractors", subcontractorService.findAll());
            model.addAttribute("startingpoints", startingpointService.findAll());
            model.addAttribute("endingpoints", endingpointService.findAll());
            return "/forwarding/form";
        }


        Forwarding forwarding = forwardingService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/forwarding/show/" + forwarding.getId();
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
        model.addAttribute("startingpoints", startingpointService.findAll());
        model.addAttribute("endingpoints", endingpointService.findAll());
        return "/forwarding/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("forwardingFilterByIntervalForm") @Valid ForwardingFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("startingpoints", startingpointService.findAll());
            model.addAttribute("endingpoints", endingpointService.findAll());
            return "/forwarding/report";
        }

        forwardingService.export(response, formData);
        return "redirect:/forwarding/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("forwardingImportAttribute", new FileUploadBean());
        return "/forwarding/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("forwardingImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/forwarding/import";
        }

        forwardingService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/forwarding/list";
    }
}
