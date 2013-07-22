package com.aripd.project.lgk.controller;

import com.aripd.account.domain.Account;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

import com.aripd.account.service.AccountService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Employeeworkinghour;
import com.aripd.project.lgk.model.EmployeeworkinghourFilterByIntervalForm;
import com.aripd.project.lgk.service.EmployeeworkinghourService;
import com.aripd.project.lgk.service.EmployeeworkinghourtypeService;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/employeeworkinghour")
public class EmployeeworkinghourController {

    @Resource(name = "employeeworkinghourService")
    private EmployeeworkinghourService employeeworkinghourService;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "employeeworkinghourtypeService")
    private EmployeeworkinghourtypeService employeeworkinghourtypeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Employeeworkinghour> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Employeeworkinghour> resultset = this.employeeworkinghourService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "employeeworkinghour/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("employeeworkinghourAttribute", employeeworkinghourService.findOne(id));
        return "employeeworkinghour/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("employeeworkinghourtypes", employeeworkinghourtypeService.findAll());
        model.addAttribute("employeeworkinghourAttribute", new Employeeworkinghour());
        return "employeeworkinghour/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("employeeworkinghourtypes", employeeworkinghourtypeService.findAll());
        model.addAttribute("employeeworkinghourAttribute", employeeworkinghourService.findOne(id));
        return "employeeworkinghour/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeworkinghourAttribute") @Valid Employeeworkinghour formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("employeeworkinghourtypes", employeeworkinghourtypeService.findAll());
            return "/employeeworkinghour/form";
        }

        Employeeworkinghour employeeworkinghour = employeeworkinghourService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/employeeworkinghour/show/" + employeeworkinghour.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Employeeworkinghour employeeworkinghour = employeeworkinghourService.findOne(id);
        employeeworkinghour.setSubmitted(true ^ employeeworkinghour.isSubmitted());
        employeeworkinghourService.save(employeeworkinghour);
        return "redirect:/employeeworkinghour/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        employeeworkinghourService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/employeeworkinghour/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("employeeworkinghourtypes", employeeworkinghourtypeService.findAll());
        model.addAttribute("employeeworkinghourFilterByIntervalForm", new EmployeeworkinghourFilterByIntervalForm());
        return "employeeworkinghour/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeworkinghourFilterByIntervalForm") @Valid EmployeeworkinghourFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("employeeworkinghourtypes", employeeworkinghourtypeService.findAll());
            return "/employeeworkinghour/report";
        }

        employeeworkinghourService.export(response, formData);
        return "redirect:/employeeworkinghour/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("employeeworkinghourImportAttribute", new FileUploadBean());
        return "employeeworkinghour/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeworkinghourImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/employeeworkinghour/import";
        }

        employeeworkinghourService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/employeeworkinghour/list";
    }
}
