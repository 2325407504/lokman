package com.aripd.project.lgk.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.model.UatfFilterByIntervalForm;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.UatfService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/uatf")
public class UatfController {

    @Resource(name = "forwardingService")
    private ForwardingService forwardingService;
    @Resource(name = "uatfService")
    private UatfService uatfService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;
    @Value("${path.directory.import}")
    String pathDirectoryImport;

    @RequestMapping(value = "/get/{forwarding_id}", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Uatf> datatablesAction(@PathVariable Long forwarding_id, @DatatablesParam DatatablesCriteria criteria) {
        return ControllerUtils.getDatatablesResultSet(criteria, this.uatfService.getRecords(forwarding_id, criteria));
    }

    @RequestMapping(value = "/save/{forwarding_id}", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @PathVariable Long forwarding_id,
            @ModelAttribute("uatfAttribute") @Valid Uatf formData,
            BindingResult result,
            Model model) {

        Forwarding forwarding = forwardingService.findOne(forwarding_id);
        if (forwarding.isSubmitted() && request.isUserInRole("ROLE_USER")) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/forwarding/list";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Bütün alanları doldurmalısınız");
            return "redirect:/forwarding/edit/" + forwarding_id;
        }

        formData.setForwarding(forwarding);

        uatfService.save(formData);
        return "redirect:/forwarding/edit/" + forwarding_id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @PathVariable Long id) {

        Uatf uatf = uatfService.findOne(id);

        if (uatf.getForwarding().isSubmitted() && request.isUserInRole("ROLE_USER")) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/forwarding/list";
        }

        uatfService.delete(uatf);
        return "redirect:/forwarding/edit/" + uatf.getForwarding().getId();
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importXLS(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("fileUploadBean") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/forwarding/import";
        }

        uatfService.importXLS(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/forwarding/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("uatfFilterByIntervalForm") @Valid UatfFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/forwarding/report";
        }

        uatfService.exportByInterval(response, startingTime, endingTime);
        return "redirect:/forwarding/report";
    }
}
