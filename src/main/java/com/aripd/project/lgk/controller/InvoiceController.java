package com.aripd.project.lgk.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.account.service.AccountService;
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

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasAnyRole({'ROLE_ADMIN','ROLE_URE'})")
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Resource(name = "invoiceService")
    private InvoiceService invoiceService;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "customerService")
    private CustomerService customerService;
    @Resource(name = "waybillService")
    private WaybillService waybillService;
    @Value("${path.directory.import}")
    String pathDirectoryImport;

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
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("invoiceAttribute", new Invoice());
        return "invoice/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("waybillFilterByDocumentNoForm", new WaybillFilterByDocumentNoForm());
        model.addAttribute("accounts", accountService.findAll());
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
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("customers", customerService.findAll());
            return "/invoice/form";
        }

        formData.setAccount(formData.getAccount());
        invoiceService.save(formData);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/invoice/list";
    }

    @RequestMapping(value = "/waybill/add/{id}", method = RequestMethod.POST)
    public String waybillAddAction(
            @PathVariable Long id,
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "documentNo", required = true) String documentNo) {
        Waybill waybill = waybillService.findOneByDocumentNo(documentNo);

        if (waybill instanceof Waybill == false) {
            redirectAttributes.addFlashAttribute("message", "Kayıt bulunamadı");
            return "redirect:/invoice/edit/" + id;
        }

        Invoice invoice = invoiceService.findOne(id);
        waybill.setInvoice(invoice);
        waybillService.save(waybill);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/invoice/edit/" + id;
    }

    @RequestMapping(value = "/waybill/remove/{id}", method = RequestMethod.POST)
    public String waybillRemoveAction(
            @PathVariable Long id,
            final RedirectAttributes redirectAttributes) {
        Waybill waybill = waybillService.findOne(id);

        if (waybill instanceof Waybill == false) {
            redirectAttributes.addFlashAttribute("message", "Kayıt bulunamadı");
            return "redirect:/invoice/list";
        }

        Long invoice_id = waybill.getInvoice().getId();
        waybill.setInvoice(null);
        waybillService.save(waybill);
        redirectAttributes.addFlashAttribute("message", "Başarı ile çıkarıldı");
        return "redirect:/invoice/edit/" + invoice_id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        invoiceService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
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

        redirectAttributes.addFlashAttribute("message", "Başarı ile tamamlandı");
        return "redirect:/invoice/report";
    }

    @RequestMapping(value = "/import/xls", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute(new FileUploadBean());
        return "invoice/import";
    }

    @RequestMapping(value = "/import/xls", method = RequestMethod.POST)
    public String importXLS(
            final RedirectAttributes redirectAttributes,
            FileUploadBean fileUploadBean,
            BindingResult result) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Hata oluştu");
            return "redirect:/invoice/import";
        }

        String fileName = null;
        try {
            MultipartFile file = fileUploadBean.getFile();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                if (file.getSize() > 1000000) {
                    redirectAttributes.addFlashAttribute("message", "Dosya boyutu büyük");
                    return "redirect:/invoice/import";
                }

                fileName = pathDirectoryImport + file.getOriginalFilename();

                outputStream = new FileOutputStream(fileName);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        invoiceService.importXLSX(fileName);
        redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
        return "redirect:/invoice/list";
    }
}
