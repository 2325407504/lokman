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
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.CsvImportBean;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Waybill;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.service.WaybillService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.SubcontractorService;
import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/waybill")
public class WaybillController {

    @Resource(name = "waybillService")
    private WaybillService waybillService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;
    @Resource(name = "subcontractorService")
    private SubcontractorService subcontractorService;
    @Resource(name = "accountService")
    private AccountService accountService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Waybill> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Waybill> resultset = this.waybillService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
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
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findAll());
        model.addAttribute("waybillAttribute", new Waybill());
        return "waybill/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("uatfAttribute", new Uatf());
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("quotas", quotaService.findAll());
        model.addAttribute("subcontractors", subcontractorService.findAll());
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
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("quotas", quotaService.findAll());
            model.addAttribute("subcontractors", subcontractorService.findAll());
            return "/waybill/form";
        }

        waybillService.save(formData);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/waybill/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Waybill waybill = waybillService.findOne(id);
        waybill.setSubmitted(true ^ waybill.isSubmitted());
        waybillService.save(waybill);
        return "redirect:/waybill/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        waybillService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/waybill/list";
    }

    /**
     * Exports the report as an Excel format.
     * <p>
     * Make sure this method doesn't return any model. Otherwise, you'll get an
     * "IllegalStateException: getOutputStream() has already been called for
     * this response"
     */
    @RequestMapping(value = "/export/xls", method = RequestMethod.GET)
    public void exportAction(HttpServletResponse response, Model model) {
        waybillService.exportXLS(response);
    }

    @RequestMapping(value = "/import/xls", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute(new FileUploadBean());
        model.addAttribute(new CsvImportBean());
        return "waybill/import";
    }
    @Value("${path.directory.import}")
    String pathDirectoryImport;

    @RequestMapping(value = "/import/xls", method = RequestMethod.POST)
    public String importXLS(
            final RedirectAttributes redirectAttributes,
            FileUploadBean fileUploadBean,
            BindingResult result) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Hata oluştu");
            return "redirect:/waybill/import";
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
                    return "redirect:/waybill/import";
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

        waybillService.importXLSX(fileName);
        redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
        return "redirect:/waybill/list";
    }
}
