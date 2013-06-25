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
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.service.RegionService;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/region")
public class RegionController {

    @Resource(name = "regionService")
    private RegionService regionService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Region> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Region> resultset = this.regionService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "region/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("regionAttribute", regionService.findOne(id));
        return "region/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("regionAttribute", new Region());
        return "region/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("regionAttribute", regionService.findOne(id));
        return "region/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("regionAttribute") @Valid Region region,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/region/form";
        }

        regionService.save(region);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/region/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        regionService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/region/list";
    }
}
