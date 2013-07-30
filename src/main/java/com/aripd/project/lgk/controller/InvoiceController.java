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
import com.aripd.project.lgk.domain.Invoice;
import com.aripd.project.lgk.domain.Waybill;
import com.aripd.project.lgk.model.InvoiceFilterByIntervalForm;
import com.aripd.project.lgk.model.WaybillFilterByDocumentNoForm;
import com.aripd.project.lgk.service.CustomerService;
import com.aripd.project.lgk.service.InvoiceService;
import com.aripd.project.lgk.service.WaybillService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Resource(name = "invoiceService")
    private InvoiceService invoiceService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "customerService")
    private CustomerService customerService;
    @Resource(name = "waybillService")
    private WaybillService waybillService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Invoice> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Invoice> resultset = this.invoiceService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/waybill/get/{invoice_id}", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Waybill> datatablesWaybillAction(@PathVariable Long invoice_id, @DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Waybill> resultset = this.waybillService.getRecords(invoice_id, criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "invoice/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("invoiceAttribute", invoiceService.findOne(id));
        return "invoice/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("invoiceAttribute", new Invoice());
        return "invoice/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("waybillFilterByDocumentNoForm", new WaybillFilterByDocumentNoForm());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("invoiceAttribute", invoiceService.findOne(id));
        return "invoice/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("invoiceAttribute") @Valid Invoice formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("customers", customerService.findAll());
            return "/invoice/form";
        }

        formData.setMember(formData.getMember());
        Invoice invoice = invoiceService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/invoice/show/" + invoice.getId();
    }

    @RequestMapping(value = "/waybill/add/{id}", method = RequestMethod.POST)
    public String waybillAddAction(
            @PathVariable Long id,
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "documentNo", required = true) String documentNo) {
        Waybill waybill = waybillService.findOneByDocumentNo(documentNo);

        if (waybill instanceof Waybill == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.found");
            return "redirect:/invoice/edit/" + id;
        }

        Invoice invoice = invoiceService.findOne(id);
        waybill.setInvoice(invoice);
        waybillService.save(waybill);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/invoice/edit/" + id;
    }

    @RequestMapping(value = "/waybill/remove/{id}", method = RequestMethod.POST)
    public String waybillRemoveAction(
            @PathVariable Long id,
            final RedirectAttributes redirectAttributes) {
        Waybill waybill = waybillService.findOne(id);

        if (waybill instanceof Waybill == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.found");
            return "redirect:/invoice/list";
        }

        Long invoice_id = waybill.getInvoice().getId();
        waybill.setInvoice(null);
        waybillService.save(waybill);
        redirectAttributes.addFlashAttribute("message", "message.completed.remove");
        return "redirect:/invoice/edit/" + invoice_id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        invoiceService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/invoice/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Invoice invoice = invoiceService.findOne(id);
        invoice.setSubmitted(true ^ invoice.isSubmitted());
        invoiceService.save(invoice);
        return "redirect:/invoice/show/" + id;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("invoiceFilterByIntervalForm", new InvoiceFilterByIntervalForm());
        return "invoice/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("invoiceFilterByIntervalForm") @Valid InvoiceFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/invoice/report";
        }

        invoiceService.exportByInterval(response, startingTime, endingTime);
        return "redirect:/invoice/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("invoiceImportAttribute", new FileUploadBean());
        return "invoice/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("invoiceImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/invoice/import";
        }

        invoiceService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/invoice/list";
    }
}
