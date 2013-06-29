package com.aripd.project.lgk.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Extrication;
import com.aripd.project.lgk.domain.Weighbridge;
import com.aripd.project.lgk.service.ExtricationService;
import com.aripd.project.lgk.service.WasteService;
import com.aripd.project.lgk.service.WeighbridgeService;
import java.security.Principal;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
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
    WebResultSet<Weighbridge> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Weighbridge> resultset = this.weighbridgeService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
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
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/weighbridge/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        weighbridgeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/weighbridge/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Weighbridge weighbridge = weighbridgeService.findOne(id);
        weighbridge.setSubmitted(true ^ weighbridge.isSubmitted());
        weighbridgeService.save(weighbridge);
        return "redirect:/weighbridge/show/" + id;
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
        weighbridgeService.exportXLS(response);
    }

    @RequestMapping(value = "/import/xls", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute(new FileUploadBean());
        return "weighbridge/import";
    }
    @Value("${path.directory.import}")
    String pathDirectoryImport;

    @RequestMapping(value = "/import/xls", method = RequestMethod.POST)
    public String importXLS(
            Principal principal,
            final RedirectAttributes redirectAttributes,
            FileUploadBean fileUploadBean,
            BindingResult result) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Hata oluştu");
            return "redirect:/weighbridge/import";
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
                    return "redirect:/weighbridge/import";
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

        weighbridgeService.importXLSX(fileName, principal);
        redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
        return "redirect:/weighbridge/list";
    }
}