package com.aripd.project.lgk.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.model.BigbagFilterByIntervalForm;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.BigbagService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_URE'))")
@Controller
@RequestMapping("/bigbag")
public class BigbagController {

    @Resource(name = "productionService")
    private ProductionService productionService;
    @Resource(name = "bigbagService")
    private BigbagService bigbagService;

    @RequestMapping(value = "/get/{production_id}", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Bigbag> datatablesAction(@PathVariable Long production_id, @DatatablesParam DatatablesCriteria criteria) {
        return ControllerUtils.getDatatablesResultSet(criteria, this.bigbagService.getRecords(production_id, criteria));
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

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("bigbagFilterByIntervalForm") @Valid BigbagFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/production/report";
        }

        bigbagService.exportByInterval(response, startingTime, endingTime);
        return "redirect:/production/report";
    }
}
