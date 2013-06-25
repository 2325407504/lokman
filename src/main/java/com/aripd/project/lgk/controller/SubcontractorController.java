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
import com.aripd.project.lgk.domain.Subcontractor;
import com.aripd.project.lgk.service.RegionService;
import com.aripd.project.lgk.service.SubcontractorService;

@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/subcontractor")
public class SubcontractorController {
	
	@Resource(name="subcontractorService")
	private SubcontractorService subcontractorService;
	
	@Resource(name="regionService")
	private RegionService regionService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Subcontractor> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Subcontractor> resultset = this.subcontractorService.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@RequestMapping(value="/list")
	public String listAction(Model model) {
		return "subcontractor/list";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(
			@PathVariable Long id,
			Model model) {
		model.addAttribute("subcontractorAttribute", subcontractorService.findOne(id));
		return "subcontractor/show";
	}
	
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
		model.addAttribute("regions", regionService.findAll());
    	model.addAttribute("subcontractorAttribute", new Subcontractor());
    	return "subcontractor/form";
	}

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
		model.addAttribute("regions", regionService.findAll());
    	model.addAttribute("subcontractorAttribute", subcontractorService.findOne(id));
    	return "subcontractor/form";
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
			final RedirectAttributes redirectAttributes,
    		@ModelAttribute("subcontractorAttribute") @Valid Subcontractor subcontractor, 
    		BindingResult result,
    		Model model) {
    	
		if (result.hasErrors()) {
			model.addAttribute("regions", regionService.findAll());
			return "/subcontractor/form";
		}
		
		subcontractorService.save(subcontractor);
		redirectAttributes.addFlashAttribute("message", "Başarı ile kaydedildi");
		return "redirect:/subcontractor/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "id", required = true) Long id) {
		subcontractorService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Başarı ile silindi");
		return "redirect:/subcontractor/list";
	}
	
}
