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
import com.aripd.project.lgk.domain.Customer;
import com.aripd.project.lgk.service.CustomerService;
import com.aripd.project.lgk.service.RegionService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@Resource(name="regionService")
	private RegionService regionService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Customer> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Customer> resultset = this.customerService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value="/list")
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
    		@ModelAttribute("customerAttribute") @Valid Customer customer, 
			BindingResult result, 
			Model model) {
    	
		if (result.hasErrors()) {
			model.addAttribute("regions", regionService.findAll());
			return "/customer/form";
		}
		
		customerService.save(customer);
		redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id) {
		customerService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
		return "redirect:/customer/list";
	}

}
