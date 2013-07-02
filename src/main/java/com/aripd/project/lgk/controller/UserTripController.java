package com.aripd.project.lgk.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.TripService;
import com.aripd.project.lgk.service.TruckService;
import com.aripd.project.lgk.validator.TripValidator;
import javax.servlet.http.HttpServletResponse;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/user/trip")
public class UserTripController {

    @Autowired
    private TripValidator tripValidator;
    @Resource(name = "tripService")
    private TripService tripService;
    @Resource(name = "truckService")
    private TruckService truckService;
    @Resource(name = "driverService")
    private DriverService driverService;
    @Resource(name = "accountService")
    private AccountService accountService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Trip> datatablesAction(
            Principal principal,
            @DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Trip> resultset = this.tripService.getRecords(principal, criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/user/trip/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Account account = accountService.findOneByUsername(principal.getName());
        Trip trip = tripService.findOneByAccountAndId(account, id);

        if (trip instanceof Trip == false) {
            redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
            return "redirect:/user/trip/list";
        }

        model.addAttribute("tripAttribute", trip);
        return "/user/trip/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(
            Principal principal,
            Model model) {

        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
        model.addAttribute("drivers", driverService.findByRegion(account.getRegion()));
        model.addAttribute("tripAttribute", new Trip());
        return "/user/trip/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Trip trip = tripService.findOne(id);
        if (trip.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/user/trip/list";
        }

        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("tripAttribute", trip);
        return "/user/trip/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("tripAttribute")/* @Valid */ Trip formData,
            BindingResult result,
            Model model) {

        tripValidator.validate(formData, result);
        if (result.hasErrors()) {
            Account account = accountService.findOneByUsername(principal.getName());
            model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
            model.addAttribute("drivers", driverService.findAll());
            return "/user/trip/form";
        }

        Account account = accountService.findOneByUsername(principal.getName());
        formData.setAccount(account);

        tripService.save(formData);
        redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
        return "redirect:/user/trip/list";
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id) {

        Account account = accountService.findOneByUsername(principal.getName());
        Trip trip = tripService.findOneByAccountAndId(account, id);

        if (trip instanceof Trip == false) {
            redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
            return "redirect:/user/trip/list";
        }

        trip.setSubmitted(true);
        tripService.save(trip);
        return "redirect:/user/trip/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @RequestParam(value = "id", required = true) Long id) {

        Account account = accountService.findOneByUsername(principal.getName());
        Trip trip = tripService.findOneByAccountAndId(account, id);

        if (trip instanceof Trip == false) {
            redirectAttributes.addFlashAttribute("message", "Bu kayda erişiminiz yetkiniz yok");
        } else if (trip.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
        } else {
            redirectAttributes.addFlashAttribute("message", "Kaydınız başarı ile silindi");
            tripService.delete(trip);
        }

        return "redirect:/user/trip/list";
    }

    @RequestMapping(value = "/report")
    public String reportAction(
            Principal principal,
            Model model) {
        Account account = accountService.findOneByUsername(principal.getName());
        model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
        return "user/trip/report";
    }

    @RequestMapping(value = "/report/truck/{id}/xls", method = RequestMethod.GET)
    public void exportByTruck(
            HttpServletResponse response,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Truck truck = truckService.findOne(id);
        tripService.exportByTruck(response, principal, truck);
    }
}
