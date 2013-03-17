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
import com.aripd.project.lgk.domain.Quota;
import com.aripd.project.lgk.service.QuotaService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/quota")
public class QuotaController {
	
	@Resource(name="quotaService")
	private QuotaService quotaService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Quota> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Quota> resultset = this.quotaService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value="/list")
	public String listAction(Model model) {
		return "quota/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(
			@PathVariable Long id,
			Model model) {
		model.addAttribute("quotaAttribute", quotaService.findOne(id));
		return "quota/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
    	model.addAttribute("quotaAttribute", new Quota());
    	return "quota/form";
	}

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(
    		@PathVariable Long id, 
    		Model model) {
    	model.addAttribute("quotaAttribute", quotaService.findOne(id));
    	return "quota/form";
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
			final RedirectAttributes redirectAttributes,
    		@ModelAttribute("quotaAttribute") @Valid Quota quota, 
			BindingResult result, 
			Model model) {
    	
		if (result.hasErrors()) {
			return "/quota/form";
		}
		
		quotaService.save(quota);
		redirectAttributes.addFlashAttribute("message", "Successfully saved..");
		return "redirect:/quota/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id) {
		quotaService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
		return "redirect:/quota/list";
	}

}