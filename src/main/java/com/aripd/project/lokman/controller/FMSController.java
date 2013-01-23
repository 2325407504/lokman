package com.aripd.project.lokman.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.util.Rotation;
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
import com.aripd.common.datatables.DataTablesRequest;
import com.aripd.common.datatables.DataTablesResponse;
import com.aripd.project.lokman.domain.FMS;
import com.aripd.project.lokman.service.DriverService;
import com.aripd.project.lokman.service.FMSService;
import com.aripd.project.lokman.service.TruckService;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

@Controller
@RequestMapping("/fms")
public class FMSController {

	protected static Logger logger4J = Logger.getLogger(FMSController.class);

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
	DataTablesResponse<Map<String, Object>> getData(@RequestBody DataTablesRequest dtReq,
			HttpServletResponse response) {
		//return new DataTablesResponse<Object>();
		
		/**/
		DataTablesResponse<Map<String, Object>> dtRes = new DataTablesResponse<Map<String, Object>>();
		
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
	@RequestMapping(value = "/list")
	public String listAction(Model model) {
		if (logger4J.isDebugEnabled()) {
			logger4J.debug("Received request to show all records");
		}
		model.addAttribute("fmsAttribute", fmsService.getAll());
		return "fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newAction(Model model) {
		logger4J.debug("Received request to show add page");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("fmsAttribute", new FMS());
		return "fms/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable Long id, Model model) {
		logger4J.debug("Received request to show edit existing record");
		model.addAttribute("trucks", truckService.getAll());
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("fmsAttribute", fmsService.getOne(id));
		return "fms/form";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@ModelAttribute("fmsAttribute") @Valid FMS formData,
			BindingResult result, Model model, Principal principal) {
		if (result.hasErrors()) {
			logger4J.error(result);
			model.addAttribute("trucks", truckService.getAll());
			model.addAttribute("drivers", driverService.getAll());
			return "/fms/form";
		}

		logger4J.debug("Received request to save existing record");

		Account account = accountService.getOneByUsername(principal.getName());
		formData.setAccount(account);

		fmsService.saveOne(formData);
		return "redirect:/fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger4J.debug("Received request to delete existing record");
		fmsService.deleteOne(id);
		return "redirect:/fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(Model model) {
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "Mac", "Mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "Windows",
				"Windows");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "Linux",
				"Linux");
		Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Others",
				"Others");

		PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);
		pieChart.setTitle("Google Pie Chart", Color.BLACK, 15);
		pieChart.setSize(720, 360);
		pieChart.setThreeD(true);

		model.addAttribute("pieUrl", pieChart.toURLString());

		return "fms/report";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/report2", method = RequestMethod.GET)
	public void report2(HttpServletResponse response) {
		response.setContentType("image/png");
		PieDataset pdSet = createDataSet();

		JFreeChart chart = createChart(pdSet, "JFreeChart Pie Chart");

		try {
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart,
					750, 400);
			response.getOutputStream().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/report3", method = RequestMethod.GET)
	public void report3(HttpServletResponse response) {
		response.setContentType("image/png");
		String query = "SELECT publishedAt, loadTon FROM fms ORDER BY publishedAt ASC";
		JDBCCategoryDataset dataset = null;
		try {
			dataset = new JDBCCategoryDataset(
					"jdbc:mysql://localhost:3306/gelibolu",
					"com.mysql.jdbc.Driver", "root", "1q2w3e4r");
			dataset.executeQuery(query);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		JFreeChart chart = ChartFactory.createBarChart3D(
				"Database Driven JFreeChart", "publishedAt", "loadTon",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		try {
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart,
					400, 300);
			response.getOutputStream().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private JFreeChart createChart(PieDataset pdSet, String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, pdSet, true,
				true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	private PieDataset createDataSet() {
		DefaultPieDataset dpd = new DefaultPieDataset();
		dpd.setValue("Mac", 21);
		dpd.setValue("Linux", 30);
		dpd.setValue("Window", 40);
		dpd.setValue("Others", 9);
		return dpd;
	}

}
