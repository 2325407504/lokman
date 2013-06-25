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
import com.aripd.project.lgk.domain.Productgroup;
import com.aripd.project.lgk.service.ProductgroupService;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/productgroup")
public class ProductgroupController {

    @Resource(name = "productgroupService")
    private ProductgroupService productgroupService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Productgroup> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Productgroup> resultset = this.productgroupService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "productgroup/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("productgroupAttribute", productgroupService.findOne(id));
        return "productgroup/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("productgroupAttribute", new Productgroup());
        return "productgroup/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("productgroupAttribute", productgroupService.findOne(id));
        return "productgroup/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("productgroupAttribute") @Valid Productgroup productgroup,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/productgroup/form";
        }

        productgroupService.save(productgroup);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/productgroup/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        productgroupService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/productgroup/list";
    }
}
