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
import com.aripd.project.lgk.domain.Machine;
import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Shift;
import com.aripd.project.lgk.service.MachineService;
import com.aripd.project.lgk.service.MachinetimeService;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.ShiftService;
import java.util.List;
import org.joda.time.DateTime;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/machine")
public class MachineController {

    @Resource(name = "machineService")
    private MachineService machineService;
    @Resource(name = "machinetimeService")
    private MachinetimeService machinetimeService;
    @Resource(name = "productionService")
    private ProductionService productionService;
    @Resource(name = "shiftService")
    private ShiftService shiftService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Machine> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Machine> resultset = this.machineService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "machine/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("machineAttribute", machineService.findOne(id));
        return "machine/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("machineAttribute", new Machine());
        return "machine/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("machineAttribute", machineService.findOne(id));
        return "machine/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("machineAttribute") @Valid Machine machine,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/machine/form";
        }

        machineService.save(machine);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/machine/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        machineService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/machine/list";
    }
}
