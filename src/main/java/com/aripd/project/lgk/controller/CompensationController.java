package com.aripd.project.lgk.controller;

import com.aripd.project.lgk.model.CompensationFilterByIntervalForm;
import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.aripd.project.lgk.service.CompensationService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/compensation")
public class CompensationController {

    @Resource(name = "compensationService")
    private CompensationService compensationService;

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("compensationFilterByIntervalForm") @Valid CompensationFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/production/report";
        }

        compensationService.exportByInterval(response, startingTime, endingTime);

        redirectAttributes.addFlashAttribute("message", "Başarı ile tamamlandı");
        return "redirect:/production/report";
    }
}
