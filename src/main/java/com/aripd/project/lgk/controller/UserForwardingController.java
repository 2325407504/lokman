package com.aripd.project.lgk.controller;

import java.security.Principal;

import javax.annotation.Resource;

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
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.model.ForwardingFilterByIntervalForm;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.EndingpointService;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.StartingpointService;
import com.aripd.project.lgk.service.SubcontractorService;
import com.aripd.project.lgk.service.TruckService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.ObjectError;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_USER') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/userforwarding")
public class UserForwardingController {

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
    @Resource(name = "driverService")
    private DriverService driverService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Forwarding> datatablesAction(
            Principal principal,
            @DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Forwarding> resultset = this.forwardingService.getRecords(principal, criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/getDriverNames", method = RequestMethod.GET)
    public @ResponseBody
    String[] getDriverNames(@RequestParam(value = "q") String q) {
        return driverService.getNames(q);
    }

    @RequestMapping(value = "/getKilometerByPlate", method = RequestMethod.GET)
    public @ResponseBody
    Integer getKilometerByPlate(@RequestParam(value = "q") String plate) {
        return truckService.getKilometer(plate);
    }

    @RequestMapping(value = "/getTruckPlates", method = RequestMethod.GET)
    public @ResponseBody
    String[] getTruckPlates(@RequestParam(value = "q") String q) {
        return truckService.getPlates(q);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/userforwarding/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Member member = memberService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByMemberAndId(member, id);

        if (forwarding instanceof Forwarding == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/userforwarding/list";
        }

        model.addAttribute("userforwardingAttribute", forwarding);
        return "/userforwarding/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            Model model) {
        Member member = memberService.findOneByUsername(principal.getName());

        if (member.getEmployee() == null) {
            redirectAttributes.addFlashAttribute("message", "message.employee.not.assigned");
            return "redirect:/userforwarding/list";
        }

        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findByRegion(member.getEmployee().getRegion()));
        model.addAttribute("startingpoints", startingpointService.findAll());
        model.addAttribute("endingpoints", endingpointService.findAll());
        model.addAttribute("userforwardingAttribute", new Forwarding());
        return "/userforwarding/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Member member = memberService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByMemberAndId(member, id);

        if (forwarding.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.editable");
            return "redirect:/userforwarding/show/" + forwarding.getId();
        }

        model.addAttribute("useruatfAttribute", new Uatf());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findByRegion(member.getEmployee().getRegion()));
        model.addAttribute("startingpoints", startingpointService.findAll());
        model.addAttribute("endingpoints", endingpointService.findAll());
        model.addAttribute("userforwardingAttribute", forwarding);
        return "/userforwarding/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("userforwardingAttribute") @Valid Forwarding formData,
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

        Member member = memberService.findOneByUsername(principal.getName());

        if (result.hasErrors()) {
            model.addAttribute("useruatfAttribute", new Uatf());
            model.addAttribute("quotas", quotaService.findAll());
            model.addAttribute("subcontractors", subcontractorService.findByRegion(member.getEmployee().getRegion()));
            model.addAttribute("startingpoints", startingpointService.findAll());
            model.addAttribute("endingpoints", endingpointService.findAll());
            return "/userforwarding/form";
        }

        formData.setMember(member);

        Forwarding forwarding = forwardingService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/userforwarding/show/" + forwarding.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id) {

        Member member = memberService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByMemberAndId(member, id);

        if (forwarding instanceof Forwarding == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/userforwarding/list";
        }

        forwarding.setSubmitted(true);
        forwardingService.save(forwarding);
        return "redirect:/userforwarding/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @RequestParam(value = "id", required = true) Long id) {

        Member member = memberService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByMemberAndId(member, id);

        if (forwarding instanceof Forwarding == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/userforwarding/list";
        } else if (forwarding.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.editable");
            return "redirect:/userforwarding/show/" + forwarding.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", "message.completed.delete");
            forwardingService.delete(forwarding);
            return "redirect:/userforwarding/list";
        }
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("userforwardingFilterByIntervalForm", new ForwardingFilterByIntervalForm());
        model.addAttribute("startingpoints", startingpointService.findAll());
        model.addAttribute("endingpoints", endingpointService.findAll());
        return "/userforwarding/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("userforwardingFilterByIntervalForm") @Valid ForwardingFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("startingpoints", startingpointService.findAll());
            model.addAttribute("endingpoints", endingpointService.findAll());
            return "/forwarding/report";
        }

        forwardingService.export(response, formData);
        return "redirect:/userforwarding/report";
    }
}
