package com.aripd.project.lokman.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aripd.account.service.IAccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lokman.domain.Uatf;
import com.aripd.project.lokman.service.DriverService;
import com.aripd.project.lokman.service.ForwardingService;
import com.aripd.project.lokman.service.QuotaService;
import com.aripd.project.lokman.service.SubcontractorService;
import com.aripd.project.lokman.service.UatfService;
import com.aripd.project.lokman.validator.UatfValidator;

@Controller
@RequestMapping("/uatf")
public class UatfController {

	protected static Logger logger = Logger.getLogger(UatfController.class);

	@Autowired
	private UatfValidator uatfValidator;

	@Resource(name = "forwardingService")
	private ForwardingService forwardingService;

	@Resource(name = "uatfService")
	private UatfService uatfService;

	@Resource(name = "quotaService")
	private QuotaService quotaService;

	@Resource(name = "subcontractorService")
	private SubcontractorService subcontractorService;

	@Resource(name = "driverService")
	private DriverService driverService;

	@Resource(name = "accountService")
	private IAccountService accountService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/get/{forwarding_id}", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Uatf> getDataTables(@PathVariable Long forwarding_id, @TableParam PagingCriteria criteria) {
		ResultSet<Uatf> customers = this.uatfService.getDataTables(forwarding_id, criteria);
		return ControllerUtils.getWebResultSet(criteria, customers);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show existing record");
		model.addAttribute("uatfAttribute", uatfService.getOne(id));
		return "uatf/show";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add page");
		model.addAttribute("uatfAttribute", new Uatf());
		return "uatf/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("uatfAttribute", uatfService.getOne(id));
		return "uatf/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save/{forwarding_id}", method = RequestMethod.POST)
	public String saveAction(
			@PathVariable Long forwarding_id, 
			@ModelAttribute("uatfAttribute")/* @Valid */Uatf formData,
			BindingResult result, Model model, Principal principal) {
		uatfValidator.validate(formData, result);
		if (result.hasErrors()) {
			logger.error(result);
			model.addAttribute("drivers", driverService.getAll());
			return "/uatf/form";
		}

		logger.debug("Received request to save existing record");
		
		formData.setForwarding(forwardingService.getOne(forwarding_id));

		uatfService.save(formData);
		return "redirect:/forwarding/show/"+forwarding_id;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable Long id) {
		logger.debug("Received request to delete existing record");
		Uatf uatf = uatfService.getOne(id);
		uatfService.delete(id);
		return "redirect:/forwarding/show/"+uatf.getForwarding().getId();
	}

	/**
	 * Exports the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/export/xls", method = RequestMethod.GET)
	public void getXLS(HttpServletResponse response, Model model) {
		logger.debug("Received request to export report as an XLS");
		uatfService.exportXLS(response);
	}

}
