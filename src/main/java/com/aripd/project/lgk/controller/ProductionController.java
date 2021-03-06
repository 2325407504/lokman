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

import com.aripd.member.service.MemberService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.model.FileUploadBean;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.model.BigbagFilterByIntervalForm;
import com.aripd.project.lgk.model.CompensationFilterByIntervalForm;
import com.aripd.project.lgk.model.MachinetimeFilterByIntervalForm;
import com.aripd.project.lgk.model.ProductionFilterByIntervalForm;
import com.aripd.project.lgk.service.CompensationService;
import com.aripd.project.lgk.service.ElectricmeterService;
import com.aripd.project.lgk.service.MachineService;
import com.aripd.project.lgk.service.MachinetimeService;
import com.aripd.project.lgk.service.ProductService;
import com.aripd.project.lgk.service.ProductionService;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_URE'))")
@Controller
@RequestMapping("/production")
public class ProductionController {

    @Resource(name = "productionService")
    private ProductionService productionService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "electricmeterService")
    private ElectricmeterService electricmeterService;
    @Resource(name = "machineService")
    private MachineService machineService;
    @Resource(name = "compensationService")
    private CompensationService compensationService;
    @Resource(name = "machinetimeService")
    private MachinetimeService machinetimeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Production> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Production> resultset = this.productionService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "/production/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("productionAttribute", productionService.findOne(id));
        return "/production/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("electricmeters", electricmeterService.findAll());
        model.addAttribute("machines", machineService.findAll());
        model.addAttribute("productionAttribute", new Production());
        return "/production/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("bigbagAttribute", new Bigbag());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("electricmeters", electricmeterService.findAll());
        model.addAttribute("machines", machineService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("productionAttribute", productionService.findOne(id));
        return "/production/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("productionAttribute") @Valid Production formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("bigbagAttribute", new Bigbag());
            model.addAttribute("members", memberService.findAll());
            model.addAttribute("electricmeters", electricmeterService.findAll());
            model.addAttribute("machines", machineService.findAll());
            model.addAttribute("products", productService.findAll());
            return "/production/form";
        }

        Production production = productionService.save(formData);

        for (Machinetime machinetime : formData.getMachinetimes()) {
            machinetime.setProduction(production);
            machinetimeService.save(machinetime);
        }

        for (Compensation compensation : formData.getCompensations()) {
            compensation.setProduction(production);
            compensationService.save(compensation);
        }

        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/production/show/" + production.getId();
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submitAction(@PathVariable Long id) {
        Production production = productionService.findOne(id);
        production.setSubmitted(true ^ production.isSubmitted());
        productionService.save(production);
        return "redirect:/production/show/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        productionService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/production/list";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("productionFilterByIntervalForm", new ProductionFilterByIntervalForm());
        model.addAttribute("bigbagFilterByIntervalForm", new BigbagFilterByIntervalForm());
        model.addAttribute("compensationFilterByIntervalForm", new CompensationFilterByIntervalForm());
        model.addAttribute("machinetimeFilterByIntervalForm", new MachinetimeFilterByIntervalForm());
        return "/production/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("productionFilterByIntervalForm") @Valid ProductionFilterByIntervalForm formData,
            BindingResult result,
            @RequestParam("startingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime startingTime,
            @RequestParam("endingTime") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") DateTime endingTime,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("productionFilterByIntervalForm", new ProductionFilterByIntervalForm());
            model.addAttribute("bigbagFilterByIntervalForm", new BigbagFilterByIntervalForm());
            model.addAttribute("compensationFilterByIntervalForm", new CompensationFilterByIntervalForm());
            model.addAttribute("machinetimeFilterByIntervalForm", new MachinetimeFilterByIntervalForm());
            return "/production/report";
        }

        productionService.export(response, startingTime, endingTime);
        return "redirect:/production/report";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAction(Model model) {
        model.addAttribute("productionImportAttribute", new FileUploadBean());
        return "/production/import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importData(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("productionImportAttribute") @Validated FileUploadBean formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/production/import";
        }

        productionService.importData(formData.getFile());
        redirectAttributes.addFlashAttribute("message", "message.completed.import");
        return "redirect:/production/list";
    }
}
