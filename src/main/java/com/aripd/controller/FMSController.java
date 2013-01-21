package com.aripd.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aripd.domain.FMS;
import com.aripd.service.DriverService;
import com.aripd.service.FMSService;
import com.aripd.service.TruckService;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

@Controller
@RequestMapping("/fms")
public class FMSController {
	
	protected static Logger logger4J = Logger.getLogger(FMSController.class);
	
	@Resource(name="fmsService")
	private FMSService fmsService;
	
	@Resource(name="truckService")
	private TruckService truckService;
	
	@Resource(name="driverService")
	private DriverService driverService;
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/list")
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
    		BindingResult result, 
    		Model model
    ) {
		if (result.hasErrors()) {
			logger4J.error(result);
			model.addAttribute("trucks", truckService.getAll());
			model.addAttribute("drivers", driverService.getAll());
			return "/fms/form";
		}
		
		logger4J.debug("Received request to save existing record");
		fmsService.save(formData);
		return "redirect:/fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		logger4J.debug("Received request to delete existing record");
		fmsService.delete(id);
		return "redirect:/fms/list";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(Model model) {
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "Mac", "Mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "Windows", "Windows");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "Linux", "Linux");
		Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Others", "Others");
		
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
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 750, 400);
			response.getOutputStream().close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private JFreeChart createChart(PieDataset pdSet, String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, pdSet, true, true, false);
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