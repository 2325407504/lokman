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
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Extrication;
import com.aripd.project.lgk.domain.Weighbridge;
import com.aripd.project.lgk.model.WeighbridgeFilterByIntervalForm;
import com.aripd.project.lgk.service.ExtricationService;
import com.aripd.project.lgk.service.WasteService;
import com.aripd.project.lgk.service.WeighbridgeService;
import java.security.Principal;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_ATY'))")
@Controller
@RequestMapping("/weighbridge")
public class WeighbridgeController {

    @Resource(name = "weighbridgeService")
    private WeighbridgeService weighbridgeService;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "wasteService")
    private WasteService wasteService;
    @Resource(name = "extricationService")
    private ExtricationService extricationService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Weighbridge> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Weighbridge> resultset = this.weighbridgeService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "weighbridge/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("weighbridgeAttribute", weighbridgeService.findOne(id));
        return "weighbridge/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("wastes", wasteService.findAll());
        model.addAttribute("weighbridgeAttribute", new Weighbridge());
        return "weighbridge/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("wastes", wasteService.findAll());
        model.addAttribute("weighbridgeAttribute", weighbridgeService.findOne(id));
        return "weighbridge/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("weighbridgeAttribute") @Valid Weighbridge formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("wastes", wasteService.findAll());
            return "/weighbridge/form";
        }

        Weighbridge weighbridge = weighbridgeService.save(formData);

        for (Extrication extrication : formData.getExtrications()) {
            extrication.setWeighbridge(weighbridge);
            extricationService.save(extrication);
        }

        weighbridgeService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/weighbridge/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        weighbridgeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/weighbridge/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Weighbridge weighbridge = weighbridgeService.findOne(id);
        weighbridge.setSubmitted(true ^ weighbridge.isSubmitted());
        weighbridgeService.save(weighbridge);
        return "redirect:/weighbridge/show/" + id;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("weighbridgeFilterByIntervalForm", new WeighbridgeFilterByIntervalForm());
        return "weighbridge/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("weighbridgeFilterByIntervalForm") @Valid WeighbridgeFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/weighbridge/report";
        }

        weighbridgeService.exportByInterval(response, startingTime, endingTime);
        return "redirect:/weighbridge/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("weighbridgeAttribute", new FileUploadBean());
        return "weighbridge/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            Principal principal,
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("weighbridgeAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/weighbridge/import";
        }

        weighbridgeService.importData(formData.getFile(), principal);
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/weighbridge/list";
    }
}
