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
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.model.CompensationFilterByIntervalForm;
import com.aripd.project.lgk.model.ProductionFilterByIntervalForm;
import com.aripd.project.lgk.service.CompensationService;
import com.aripd.project.lgk.service.ElectricmeterService;
import com.aripd.project.lgk.service.MachineService;
import com.aripd.project.lgk.service.MachinetimeService;
import com.aripd.project.lgk.service.ProductService;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.ShiftService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/production")
public class ProductionController {

    @Resource(name = "productionService")
    private ProductionService productionService;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "shiftService")
    private ShiftService shiftService;
    @Resource(name = "electricmeterService")
    private ElectricmeterService electricmeterService;
    @Resource(name = "machineService")
    private MachineService machineService;
    @Resource(name = "compensationService")
    private CompensationService compensationService;
    @Resource(name = "machinetimeService")
    private MachinetimeService machinetimeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Production> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Production> resultset = this.productionService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "production/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("productionAttribute", productionService.findOne(id));
        return "production/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("shifts", shiftService.findAll());
        model.addAttribute("electricmeters", electricmeterService.findAll());
        model.addAttribute("machines", machineService.findAll());
        Production production = new Production();
        production.setShiftdate(new DateTime());
        model.addAttribute("productionAttribute", production);
        return "production/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("bigbagAttribute", new Bigbag());
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("shifts", shiftService.findAll());
        model.addAttribute("electricmeters", electricmeterService.findAll());
        model.addAttribute("machines", machineService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("productionAttribute", productionService.findOne(id));
        return "production/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("productionAttribute") @Valid Production formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("shifts", shiftService.findAll());
            model.addAttribute("electricmeters", electricmeterService.findAll());
            model.addAttribute("machines", machineService.findAll());
            return "/production/form";
        }

        Production production = productionService.save(formData);

        for (Machinetime machinetime : formData.getMachinetimes()) {
            machinetime.setProduction(production);
            machinetimeService.save(machinetime);
        }

        for (Compensation compensation : formData.getCompensations()) {
            compensation.setProduction(production);
            compensationService.save(compensation);
        }

        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/production/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Production production = productionService.findOne(id);
        production.setSubmitted(true ^ production.isSubmitted());
        productionService.save(production);
        return "redirect:/production/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        productionService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/production/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("productionFilterByIntervalForm", new ProductionFilterByIntervalForm());
        model.addAttribute("compensationFilterByIntervalForm", new CompensationFilterByIntervalForm());
        return "production/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("productionFilterByIntervalForm") @Valid ProductionFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/production/report";
        }

        productionService.exportByInterval(response, startingTime, endingTime);

        redirectAttributes.addFlashAttribute("message", "Başarı ile tamamlandı");
        return "redirect:/production/report";
    }

    @RequestMapping(value = "/import/xls", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute(new FileUploadBean());
        return "production/import";
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

        productionService.importXLSX(fileName);
        redirectAttributes.addFlashAttribute("message", "İçe aktarım başarı ile tamamlandı");
        return "redirect:/production/list";
    }
}
