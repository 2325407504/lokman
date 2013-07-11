package com.aripd.project.lgk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.model.TripFilterByIntervalAndTruckForm;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.TripService;
import com.aripd.project.lgk.service.TruckService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/trip")
public class TripController {

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
    WebResultSet<Trip> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Trip> resultset = this.tripService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "trip/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("tripAttribute", tripService.findOne(id));
        return "trip/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("trucks", truckService.findAll());
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("tripAttribute", new Trip());
        return "trip/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            final RedirectAttributes redirectAttributes,
            @PathVariable Long id,
            Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("trucks", truckService.findAll());
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("tripAttribute", tripService.findOne(id));
        return "trip/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("tripAttribute") @Valid Trip formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            model.addAttribute("trucks", truckService.findAll());
            model.addAttribute("drivers", driverService.findAll());
            return "/trip/form";
        }

        Trip trip = tripService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/trip/show/" + trip.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Trip trip = tripService.findOne(id);
        trip.setSubmitted(true ^ trip.isSubmitted());
        tripService.save(trip);
        return "redirect:/trip/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        tripService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/trip/list";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("tripImportAttribute", new FileUploadBean());
        return "trip/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("tripImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/trip/import";
        }

        tripService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/trip/list";
    }

    @RequestMapping(value = "/chart")
    public String chartAction(Model model) {
        return "trip/chart";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("tripFilterByIntervalAndTruckForm", new TripFilterByIntervalAndTruckForm());
        model.addAttribute("trucks", truckService.findAll());
        return "trip/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("tripFilterByIntervalAndTruckForm") @Valid TripFilterByIntervalAndTruckForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            @RequestParam("truck.id") Long truck_id,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            return "/trip/report";
        }

        if (truck_id == null) {
            tripService.exportByInterval(response, startingTime, endingTime);
        } else {
            tripService.exportByIntervalAndTruck(response, startingTime, endingTime, truckService.findOne(truck_id));
        }
        return "redirect:/trip/report";
    }
}
