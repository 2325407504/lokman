package com.aripd.project.lgk.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

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

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lgk.domain.Shift;
import com.aripd.project.lgk.service.ShiftService;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/shift")
public class ShiftController {

    @Resource(name = "shiftService")
    private ShiftService shiftService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Shift> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Shift> resultset = this.shiftService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "shift/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("shiftAttribute", shiftService.findOne(id));
        return "shift/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("shiftAttribute", new Shift());
        return "shift/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("shiftAttribute", shiftService.findOne(id));
        return "shift/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("shiftAttribute") @Valid Shift shift,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/shift/form";
        }

        shiftService.save(shift);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/shift/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        shiftService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/shift/list";
    }
}
