package com.aripd.project.lgk.controller;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
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

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Disposalcost;
import com.aripd.project.lgk.domain.Customer;
import com.aripd.project.lgk.domain.Shippingcost;
import com.aripd.project.lgk.service.DisposalcostService;
import com.aripd.project.lgk.service.CustomerService;
import com.aripd.project.lgk.service.RegionService;
import com.aripd.project.lgk.service.ShippingcostService;
import org.apache.commons.codec.digest.DigestUtils;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource(name = "customerService")
    private CustomerService customerService;
    @Resource(name = "regionService")
    private RegionService regionService;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "shippingcostService")
    private ShippingcostService shippingcostService;
    @Resource(name = "disposalcostService")
    private DisposalcostService disposalcostService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Customer> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Customer> resultset = this.customerService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return "customer/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("customerAttribute", customerService.findOne(id));
        return "customer/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("customerAttribute", new Customer());
        return "customer/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("customerAttribute", customerService.findOne(id));
        return "customer/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("customerAttribute") @Valid Customer formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("regions", regionService.findAll());
            return "/customer/form";
        }

        if (formData.getAuthorized().getId() != null) {
            Account account = accountService.findOne(formData.getAuthorized().getId());
            if (formData.getAuthorized().getPassword().length() == 0) {
                formData.getAuthorized().setPassword(account.getPassword());
            } else {
                formData.getAuthorized().setPassword(DigestUtils.md5Hex(formData.getAuthorized().getPassword()));
            }
        } else {
            formData.getAuthorized().setPassword(DigestUtils.md5Hex(formData.getAuthorized().getPassword()));
        }

        Customer customer = customerService.save(formData);
        
        /**
         * TODO
         * sadece customer kaydedildiğinde shippingcost ve disposalcost bilgilerinin tekrar tekrar aynı değerleri kaydetmesini nasıl engelleriz?
         */
        if (formData.getShippingcost() != null) {
            Shippingcost shippingcost = new Shippingcost();
            shippingcost.setCustomer(customer);
            shippingcost.setCost(formData.getShippingcost());
            shippingcostService.save(shippingcost);
        }
        if (formData.getDisposalcost() != null) {
            Disposalcost disposalcost = new Disposalcost();
            disposalcost.setCustomer(customer);
            disposalcost.setCost(formData.getDisposalcost());
            disposalcostService.save(disposalcost);
        }

        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/customer/show/" + customer.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @RequestParam(value = "id", required = true) Long id) {
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("message", "message.completed.delete");
        return "redirect:/customer/list";
    }
}
