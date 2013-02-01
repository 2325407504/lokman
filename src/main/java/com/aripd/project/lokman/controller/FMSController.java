package com.aripd.project.lokman.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.DataTablesRequestDto;
import com.aripd.common.dto.DataTablesResponseDto;
import com.aripd.project.lokman.domain.FMS;
import com.aripd.project.lokman.service.DriverService;
import com.aripd.project.lokman.service.FMSService;
import com.aripd.project.lokman.service.TruckService;
import com.aripd.project.lokman.validator.FMSValidator;

@Controller
@RequestMapping("/fms")
public class FMSController {

	protected static Logger logger = Logger.getLogger(FMSController.class);

	@Autowired
	private FMSValidator fmsValidator;
	
	@Resource(name = "fmsService")
	private FMSService fmsService;

	@Resource(name = "truckService")
	private TruckService truckService;

	@Resource(name = "driverService")
	private DriverService driverService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public @ResponseBody
	DataTablesResponseDto<Map<String, Object>> getData(@RequestBody DataTablesRequestDto dtReq,
			HttpServletResponse response) {
		//return new DataTablesResponse<Object>();
		
		/**/
		DataTablesResponseDto<Map<String, Object>> dtRes = new DataTablesResponseDto<Map<String, Object>>();
		
		List<FMS> fmsList = fmsService.getAll();
		List<Map<String,Object>> listDataTable = new ArrayList<Map<String, Object>>();
		if (fmsList != null) {
        	for (FMS fms : fmsList) {
        		Map<String, Object> mapDataRows = new HashMap<String, Object>();
        		mapDataRows.put("key", fms.getId() != null ? fms.getId().toString() : StringUtils.EMPTY);
        		mapDataRows.put("name", fms.getPublishedAt().toString());
        		mapDataRows.put("username", fms.getAccount().getUsername() != null ? fms.getAccount().getUsername() : StringUtils.EMPTY);
        		listDataTable.add(mapDataRows);
			}
        }
		dtRes.echo = dtReq.echo;
		dtRes.totalRecords = listDataTable.size();
		dtRes.totalDisplayRecords = listDataTable.size();
		int endSubList = ((dtReq.displayStart+dtReq.displayLength)>listDataTable.size())?listDataTable.size():(dtReq.displayStart+dtReq.displayLength);
		dtRes.data = listDataTable.subList(dtReq.displayStart, endSubList);
		dtRes.columns = "{key,name,username}";
    	
		return dtRes;
		/**/
		
		/*
		String str = "{  \"sEcho\": 2 ," +
	            "   \"iTotalRecords\": 2," +
	            "   \"iTotalDisplayRecords\": 2," +
	            "   \"aaData\": [" +
	            "       [" +
	            "           \"Gecko\"," +
	            "           \"Firefox 1.0\"," +
	            "           \"Win 98+ / OSX.2+\"," +
	            "           \"1.7\"," +
	            "           \"A\"" +
	            "       ]," +
	            "       [" +
	            "           \"Gecko\"," +
	            "           \"Firefox 1.5\"," +
	            "           \"Win 98+ / OSX.2+\"," +
	            "           \"1.8\"," +
	            "           \"A\"" +
	            "       ]" +
	            "   ]" +
	            "}";
	 
	        return str;
	        */
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/chart")
	public String chartAction(Model model) {
		return "fms/chart";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received request to show all records");
		}
		model.addAttribute("fmsAttribute", fmsService.getAll());
		return "fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger.debug("Received request to show add page");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("fmsAttribute", new FMS());
		return "fms/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger.debug("Received request to show edit existing record");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("fmsAttribute", fmsService.getOne(id));
		return "fms/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("fmsAttribute") /*@Valid*/ FMS formData,
			BindingResult result, Model model, Principal principal) {
		fmsValidator.validate(formData, result);
		if (result.hasErrors()) {
			logger.error(result);
			model.addAttribute("trucks", truckService.getAll());
			model.addAttribute("drivers", driverService.getAll());
			return "/fms/form";
		}

		logger.debug("Received request to save existing record");

		Account account = accountService.getOneByUsername(principal.getName());
		formData.setAccount(account);

		fmsService.save(formData);
		return "redirect:/fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger.debug("Received request to delete existing record");
		fmsService.delete(id);
		return "redirect:/fms/list";
	}

}
