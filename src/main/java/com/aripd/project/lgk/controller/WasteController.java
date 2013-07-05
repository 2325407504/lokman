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

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Waste;
import com.aripd.project.lgk.service.WasteService;
import com.aripd.project.lgk.service.WastegroupService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_ATY'))")
@Controller
@RequestMapping("/waste")
public class WasteController {

    @Resource(name = "wasteService")
    private WasteService wasteService;
    @Resource(name = "wastegroupService")
    private WastegroupService wastegroupService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Waste> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Waste> resultset = this.wasteService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "waste/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("wasteAttribute", wasteService.findOne(id));
        return "waste/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("wastegroups", wastegroupService.findAll());
        model.addAttribute("wasteAttribute", new Waste());
        return "waste/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("wastegroups", wastegroupService.findAll());
        model.addAttribute("wasteAttribute", wasteService.findOne(id));
        return "waste/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("wasteAttribute") @Valid Waste waste,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/waste/form";
        }

        wasteService.save(waste);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/waste/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        wasteService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/waste/list";
    }
}
