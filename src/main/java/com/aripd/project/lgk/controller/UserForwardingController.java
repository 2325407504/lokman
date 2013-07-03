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

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.SubcontractorService;
import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/userforwarding")
public class UserForwardingController {

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
    WebResultSet<Forwarding> datatablesAction(
            Principal principal,
            @DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Forwarding> resultset = this.forwardingService.getRecords(principal, criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
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

        Account account = accountService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByAccountAndId(account, id);

        if (forwarding instanceof Forwarding == false) {
            redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
            return "redirect:/userforwarding/list";
        }

        model.addAttribute("userforwardingAttribute", forwarding);
        return "/userforwarding/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(
            Principal principal,
            Model model) {
        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findByRegion(account.getRegion()));
        model.addAttribute("userforwardingAttribute", new Forwarding());
        return "/userforwarding/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Forwarding forwarding = forwardingService.findOne(id);
        if (forwarding.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/userforwarding/show/" + forwarding.getId();
        }

        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("useruatfAttribute", new Uatf());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findByRegion(account.getRegion()));
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

        if (result.hasErrors()) {
            Account account = accountService.findOneByUsername(principal.getName());

            model.addAttribute("quotas", quotaService.findAll());
            model.addAttribute("subcontractors", subcontractorService.findByRegion(account.getRegion()));
            return "/userforwarding/form";
        }

        Account account = accountService.findOneByUsername(principal.getName());
        formData.setAccount(account);

        forwardingService.save(formData);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/userforwarding/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Account account = accountService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByAccountAndId(account, id);

        if (forwarding instanceof Forwarding == false) {
            redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
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

        Account account = accountService.findOneByUsername(principal.getName());
        Forwarding forwarding = forwardingService.findOneByAccountAndId(account, id);

        if (forwarding instanceof Forwarding == false) {
            redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
            return "redirect:/userforwarding/list";
        } else if (forwarding.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/userforwarding/show/" + forwarding.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", "Kaydınız başarı ile silindi");
            forwardingService.delete(forwarding);
            return "redirect:/userforwarding/list";
        }
    }
}
