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
import com.aripd.project.lgk.domain.Employeeworkinghourtype;
import com.aripd.project.lgk.service.EmployeeworkinghourtypeService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/employeeworkinghourtype")
public class EmployeeworkinghourtypeController {

    @Resource(name = "employeeworkinghourtypeService")
    private EmployeeworkinghourtypeService employeeworkinghourtypeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Employeeworkinghourtype> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Employeeworkinghourtype> resultset = this.employeeworkinghourtypeService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/employeeworkinghourtype/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("employeeworkinghourtypeAttribute", employeeworkinghourtypeService.findOne(id));
        return "/employeeworkinghourtype/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("employeeworkinghourtypeAttribute", new Employeeworkinghourtype());
        return "/employeeworkinghourtype/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("employeeworkinghourtypeAttribute", employeeworkinghourtypeService.findOne(id));
        return "/employeeworkinghourtype/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeworkinghourtypeAttribute") @Valid Employeeworkinghourtype formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/employeeworkinghourtype/form";
        }

        Employeeworkinghourtype employeeworkinghourtype = employeeworkinghourtypeService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/employeeworkinghourtype/show/" + employeeworkinghourtype.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        employeeworkinghourtypeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/employeeworkinghourtype/list";
    }
}
