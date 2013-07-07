package com.aripd.project.lgk.controller;

import java.security.Principal;

import javax.annotation.Resource;

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
import com.aripd.project.lgk.model.TripFilterByIntervalAndTruckForm;
import com.aripd.project.lgk.model.UserTripFilterByIntervalAndTruckForm;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.TripService;
import com.aripd.project.lgk.service.TruckService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_USER') and hasRole('ROLE_OTL'))")
@Controller
@RequestMapping("/usertrip")
public class UserTripController {

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
        return "/usertrip/list";
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
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/usertrip/list";
        }

        model.addAttribute("usertripAttribute", trip);
        return "/usertrip/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(
            Principal principal,
            Model model) {

        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
        model.addAttribute("drivers", driverService.findByRegion(account.getRegion()));
        model.addAttribute("usertripAttribute", new Trip());
        return "/usertrip/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id,
            Model model) {

        Trip trip = tripService.findOne(id);
        if (trip.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.editable");
            return "redirect:/usertrip/show/" + trip.getId();
        }

        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("usertripAttribute", trip);
        return "/usertrip/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("usertripAttribute") @Valid Trip formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            Account account = accountService.findOneByUsername(principal.getName());
            model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
            model.addAttribute("drivers", driverService.findAll());
            return "/usertrip/form";
        }

        Account account = accountService.findOneByUsername(principal.getName());
        formData.setAccount(account);

        Trip trip = tripService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/usertrip/show/" + trip.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @PathVariable Long id) {

        Account account = accountService.findOneByUsername(principal.getName());
        Trip trip = tripService.findOneByAccountAndId(account, id);

        if (trip instanceof Trip == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/usertrip/list";
        }

        trip.setSubmitted(true);
        tripService.save(trip);
        return "redirect:/usertrip/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @RequestParam(value = "id", required = true) Long id) {

        Account account = accountService.findOneByUsername(principal.getName());
        Trip trip = tripService.findOneByAccountAndId(account, id);

        if (trip instanceof Trip == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/usertrip/list";
        } else if (trip.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.editable");
            return "redirect:/usertrip/show/" + trip.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", "message.completed.delete");
            tripService.delete(trip);
            return "redirect:/usertrip/list";
        }
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(
            Principal principal,
            Model model) {
        Account account = accountService.findOneByUsername(principal.getName());
        model.addAttribute("usertripFilterByIntervalAndTruckForm", new UserTripFilterByIntervalAndTruckForm());
        model.addAttribute("trucks", truckService.findByRegion(account.getRegion()));
        return "usertrip/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("usertripFilterByIntervalAndTruckForm") @Valid UserTripFilterByIntervalAndTruckForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            @RequestParam("truck.id") Long truck_id,
            HttpServletResponse response,
            Principal principal,
            Model model) {

        if (result.hasErrors()) {
            return "/usertrip/report";
        }

        tripService.exportByIntervalAndTruckAndPrincipal(response, startingTime, endingTime, truckService.findOne(truck_id), principal);
        return "redirect:/usertrip/report";
    }
}
