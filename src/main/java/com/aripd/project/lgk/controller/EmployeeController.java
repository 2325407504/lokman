package com.aripd.project.lgk.controller;

import com.aripd.project.lgk.domain.Employee;
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

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.service.EmployeeService;
import com.aripd.project.lgk.service.EmployeeleaveService;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final String PATH = "/employee";
    private static final String LIST_VIEW = PATH + "/list";
    private static final String SHOW_VIEW = PATH + "/show";
    private static final String FORM_VIEW = PATH + "/form";
    private static final String IMPORT_VIEW = PATH + "/import";
    private static final String REPORT_VIEW = PATH + "/report";
    @Resource(name = "employeeService")
    private EmployeeService employeeService;
    @Resource(name = "employeeleaveService")
    private EmployeeleaveService employeeleaveService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Employee> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Employee> resultset = this.employeeService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return LIST_VIEW;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("employeeAttribute", employeeService.findOne(id));
        return SHOW_VIEW;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("employeeAttribute", new Employee());
        return FORM_VIEW;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("employeeAttribute", employeeService.findOne(id));
        return FORM_VIEW;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeAttribute") @Valid Employee formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return FORM_VIEW;
        }

        Employee employee = employeeService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/employee/show/" + employee.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        employeeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:" + LIST_VIEW;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction() {
        return REPORT_VIEW;
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(HttpServletResponse response) {
        employeeService.exportData(response);
        return "redirect:" + REPORT_VIEW;
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("employeeImportAttribute", new FileUploadBean());
        return IMPORT_VIEW;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("employeeImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return IMPORT_VIEW;
        }

        employeeService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:" + LIST_VIEW;
    }
}
