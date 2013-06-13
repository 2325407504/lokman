package com.aripd.project.lgk.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.SubcontractorService;
import com.aripd.project.lgk.service.BigbagService;
import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/bigbag")
public class BigbagController {

    @Resource(name = "productionService")
    private ProductionService productionService;
    @Resource(name = "bigbagService")
    private BigbagService bigbagService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;
    @Resource(name = "subcontractorService")
    private SubcontractorService subcontractorService;
    @Resource(name = "driverService")
    private DriverService driverService;
    @Resource(name = "accountService")
    private AccountService accountService;

    @RequestMapping(value = "/get/{production_id}", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Bigbag> getDataTables(@PathVariable Long production_id, @TableParam PagingCriteria criteria) {
        ResultSet<Bigbag> customers = this.bigbagService.getRecords(production_id, criteria);
        return ControllerUtils.getWebResultSet(criteria, customers);
    }

    @RequestMapping(value = "/save/{production_id}", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @PathVariable Long production_id,
            @ModelAttribute("bigbagAttribute") @Valid Bigbag formData,
            BindingResult result,
            Model model) {

        Production production = productionService.findOne(production_id);
        if (production.isSubmitted() && request.isUserInRole("ROLE_USER")) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/production/list";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Bütün alanları doldurmalısınız");
            return "redirect:/production/edit/" + production_id;
        }

        formData.setProduction(production);

        bigbagService.save(formData);
        return "redirect:/production/edit/" + production_id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @PathVariable Long id) {

        Bigbag bigbag = bigbagService.findOne(id);

        if (bigbag.getProduction().isSubmitted() && request.isUserInRole("ROLE_USER")) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/production/list";
        }

        bigbagService.delete(bigbag);
        return "redirect:/production/edit/" + bigbag.getProduction().getId();
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
            return "redirect:/production/import";
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
                    return "redirect:/production/import";
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

        bigbagService.importXLSX(fileName);
        redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
        return "redirect:/production/list";
    }
}
