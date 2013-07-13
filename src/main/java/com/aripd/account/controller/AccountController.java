package com.aripd.account.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
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

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Role;
import com.aripd.account.service.AccountService;
import com.aripd.account.service.EmployeeService;
import com.aripd.account.service.RoleService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.service.RegionService;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "regionService")
    private RegionService regionService;
    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    /**
     * TODO Bunun yerine başka ne yapabiliriz?
     *
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "roles", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof Role) {
                    return element;
                }
                if (element instanceof String) {
                    return roleService.findOne(Long.valueOf(element.toString()));
                }
                return null;
            }
        });
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Account> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Account> resultset = this.accountService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAction(Model model) {
        return "account/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("accountAttribute", accountService.findOne(id));
        return "account/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("accountAttribute", new Account());
        return "account/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("accountAttribute", accountService.findOne(id));
        return "account/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("accountAttribute") @Valid Account formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("regions", regionService.findAll());
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            return "/account/form";
        }

        if (formData.getId() != null) {
            Account account = accountService.findOne(formData.getId());
            if (formData.getPassword().length() == 0) {
                formData.setPassword(account.getPassword());
            } else {
                formData.setPassword(DigestUtils.md5Hex(formData.getPassword()));
            }
        } else {
            formData.setPassword(DigestUtils.md5Hex(formData.getPassword()));
        }

        Account a = accountService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/account/show/" + a.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id", required = true) Long id) {
        accountService.delete(id);
        return "redirect:/account/list";
    }
}
