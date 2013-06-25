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
import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.service.RegionService;
import com.aripd.project.lgk.service.TruckService;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/truck")
public class TruckController {

    @Resource(name = "truckService")
    private TruckService truckService;
    @Resource(name = "regionService")
    private RegionService regionService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Truck> getDataTables(@TableParam PagingCriteria criteria) {
        ResultSet<Truck> resultset = this.truckService.getRecords(criteria);
        return ControllerUtils.getWebResultSet(criteria, resultset);
    }

    /**
     * TODO Authorize ile ilgili sorun çıkacak
     *
     * @param plate
     * @return
     */
    @RequestMapping(value = "/get/{id}/kilometer", method = RequestMethod.GET)
    public @ResponseBody
    Integer getKilometer(@PathVariable Long id) {
        return truckService.getKilometer(id);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "truck/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("truckAttribute", truckService.findOne(id));
        return "truck/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("truckAttribute", new Truck());
        return "truck/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("truckAttribute", truckService.findOne(id));
        return "truck/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("truckAttribute") @Valid Truck truck,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("regions", regionService.findAll());
            return "/truck/form";
        }

        truckService.save(truck);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/truck/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        truckService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
        return "redirect:/truck/list";
    }
}
