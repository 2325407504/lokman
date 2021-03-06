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
import com.aripd.common.dto.autocomplete.AutocompleteCriteria;
import com.aripd.common.dto.autocomplete.AutocompleteParam;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Outgoing;
import com.aripd.project.lgk.domain.Waybill;
import com.aripd.project.lgk.model.WaybillFilterByIntervalForm;
import com.aripd.project.lgk.model.OutgoingFilterByIntervalForm;
import com.aripd.project.lgk.service.ProductService;
import com.aripd.project.lgk.service.WaybillService;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/waybill")
public class WaybillController {

    @Resource(name = "waybillService")
    private WaybillService waybillService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "productService")
    private ProductService productService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Waybill> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Waybill> resultset = this.waybillService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public @ResponseBody
    List<String> autocompleteAction(@AutocompleteParam AutocompleteCriteria criteria) {
        return this.waybillService.getRecords(criteria);
        //AutocompleteResultSet<Waybill> resultset = this.waybillService.getRecords(criteria);
        //return ControllerUtils.getAutocompleteResultSet(resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "waybill/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("waybillAttribute", waybillService.findOne(id));
        return "waybill/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("waybillAttribute", new Waybill());
        return "waybill/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("outgoingAttribute", new Outgoing());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("waybillAttribute", waybillService.findOne(id));
        return "waybill/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("waybillAttribute") @Valid Waybill formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("outgoingAttribute", new Outgoing());
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("products", productService.findAll());
            return "/waybill/form";
        }

        Waybill waybill = waybillService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/waybill/show/" + waybill.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        waybillService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/waybill/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Waybill waybill = waybillService.findOne(id);
        waybill.setSubmitted(true ^ waybill.isSubmitted());
        waybillService.save(waybill);
        return "redirect:/waybill/show/" + id;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("waybillFilterByIntervalForm", new WaybillFilterByIntervalForm());
        model.addAttribute("outgoingFilterByIntervalForm", new OutgoingFilterByIntervalForm());
        return "waybill/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("waybillFilterByIntervalForm") @Valid WaybillFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/waybill/report";
        }

        waybillService.export(response, startingTime, endingTime);
        return "redirect:/waybill/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("waybillImportAttribute", new FileUploadBean());
        return "waybill/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("waybillImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/waybill/import";
        }

        waybillService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/waybill/list";
    }
}
