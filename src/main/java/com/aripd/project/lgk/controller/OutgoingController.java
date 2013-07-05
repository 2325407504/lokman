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
import com.aripd.project.lgk.domain.Waybill;
import com.aripd.project.lgk.domain.Outgoing;
import com.aripd.project.lgk.model.OutgoingFilterByIntervalForm;
import com.aripd.project.lgk.service.WaybillService;
import com.aripd.project.lgk.service.OutgoingService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_URE'))")
@Controller
@RequestMapping("/outgoing")
public class OutgoingController {

    @Resource(name = "waybillService")
    private WaybillService waybillService;
    @Resource(name = "outgoingService")
    private OutgoingService outgoingService;
    @Value("${path.directory.import}")
    String pathDirectoryImport;

    @RequestMapping(value = "/get/{waybill_id}", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Outgoing> datatablesAction(@PathVariable Long waybill_id, @DatatablesParam DatatablesCriteria criteria) {
        return ControllerUtils.getDatatablesResultSet(criteria, this.outgoingService.getRecords(waybill_id, criteria));
    }

    @RequestMapping(value = "/save/{waybill_id}", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @PathVariable Long waybill_id,
            @ModelAttribute("outgoingAttribute") @Valid Outgoing formData,
            BindingResult result,
            Model model) {

        Waybill waybill = waybillService.findOne(waybill_id);
        if (waybill.isSubmitted() && request.isUserInRole("ROLE_USER")) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/waybill/list";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Bütün alanları doldurmalısınız");
            return "redirect:/waybill/edit/" + waybill_id;
        }

        formData.setWaybill(waybill);

        outgoingService.save(formData);
        return "redirect:/waybill/edit/" + waybill_id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            final RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @PathVariable Long id) {

        Outgoing outgoing = outgoingService.findOne(id);

        if (outgoing.getWaybill().isSubmitted() && request.isUserInRole("ROLE_USER")) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/waybill/list";
        }

        outgoingService.delete(outgoing);
        return "redirect:/waybill/edit/" + outgoing.getWaybill().getId();
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importXLS(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("fileUploadBean") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/waybill/import";
        }

        outgoingService.importXLS(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/waybill/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("outgoingFilterByIntervalForm") @Valid OutgoingFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/waybill/report";
        }

        outgoingService.exportByInterval(response, startingTime, endingTime);
        return "redirect:/waybill/report";
    }
}
