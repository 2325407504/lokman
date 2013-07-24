package com.aripd.account.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import com.aripd.account.domain.Role;
import com.aripd.account.service.RoleService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/role")
public class RoleController {

    protected static Logger logger = Logger.getLogger(RoleController.class);
    private static final String PATH = "/role";
    private static final String LIST_VIEW = PATH + "/list";
    private static final String SHOW_VIEW = PATH + "/show";
    private static final String FORM_VIEW = PATH + "/form";
    @Resource(name = "roleService")
    private RoleService roleService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Role> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Role> resultset = this.roleService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAction(Model model) {
        return LIST_VIEW;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        logger.debug("Received request to show a record");
        model.addAttribute("roleAttribute", roleService.findOne(id));
        return SHOW_VIEW;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        logger.debug("Received request to add a new record");
        model.addAttribute("roleAttribute", new Role());
        return FORM_VIEW;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        logger.debug("Received request to edit an existing record");
        model.addAttribute("roleAttribute", roleService.findOne(id));
        return FORM_VIEW;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("roleAttribute") @Valid Role formData,
            BindingResult result) {
        if (result.hasErrors()) {
            logger.error(result);
            return FORM_VIEW;
        }

        logger.debug("Received request to save a record");
        Role role = roleService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/role/show/" + role.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        logger.debug("Received request to delete an existing record");
        roleService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:" + LIST_VIEW;
    }
}
