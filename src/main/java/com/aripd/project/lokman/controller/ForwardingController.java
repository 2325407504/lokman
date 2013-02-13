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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.TableParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.utils.ControllerUtils;
import com.aripd.project.lokman.domain.Forwarding;
import com.aripd.project.lokman.domain.Uatf;
import com.aripd.project.lokman.service.DriverService;
import com.aripd.project.lokman.service.ForwardingService;
import com.aripd.project.lokman.service.QuotaService;
import com.aripd.project.lokman.service.SubcontractorService;
import com.aripd.project.lokman.service.UatfService;
import com.aripd.project.lokman.validator.ForwardingValidator;

@Controller
@RequestMapping("/forwarding")
public class ForwardingController {

	protected static Logger logger = Logger.getLogger(ForwardingController.class);

	@Autowired
	private ForwardingValidator forwardingValidator;

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
	private AccountService accountService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<Forwarding> getDataTables(@TableParam PagingCriteria criteria) {
		ResultSet<Forwarding> resultset = this.forwardingService.getDataTables(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		return "forwarding/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show existing record");
		model.addAttribute("uatfAttribute", new Uatf());
		model.addAttribute("forwardingAttribute", forwardingService.getOne(id));
		return "forwarding/show";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add page");
		model.addAttribute("quotas", quotaService.getAll());
		model.addAttribute("subcontractors", subcontractorService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("forwardingAttribute", new Forwarding());
		return "forwarding/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("quotas", quotaService.getAll());
		model.addAttribute("subcontractors", subcontractorService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("forwardingAttribute", forwardingService.getOne(id));
		return "forwarding/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("forwardingAttribute")/* @Valid */Forwarding formData,
			BindingResult result, Model model, Principal principal) {
		forwardingValidator.validate(formData, result);
		if (result.hasErrors()) {
			logger.error(result);
			model.addAttribute("quotas", quotaService.getAll());
			model.addAttribute("subcontractors", subcontractorService.getAll());
			model.addAttribute("drivers", driverService.getAll());
			return "/forwarding/form";
		}

		logger.debug("Received request to save existing record");

		Account account = accountService.getOneByUsername(principal.getName());
		formData.setAccount(account);

		forwardingService.save(formData);
		return "redirect:/forwarding/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		forwardingService.delete(id);
		return "redirect:/forwarding/list";
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
		forwardingService.exportXLS(response);
	}

}
