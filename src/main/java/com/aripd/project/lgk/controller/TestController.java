package com.aripd.project.lgk.controller;

import com.aripd.project.lgk.service.ForwardingService;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;
import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    protected static Logger logger = Logger.getLogger(TestController.class);
    @Resource(name = "forwardingService")
    private ForwardingService forwardingService;

    @RequestMapping(value = "/chart")
    public String chartAction(Model model) {
        return "/test/chart";
    }

    @RequestMapping(value = "/report1", method = RequestMethod.GET)
    public String report1(Model model) {
        Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "Mac", "Mac");
        Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "Windows", "Windows");
        Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "Linux", "Linux");
        Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Others", "Others");

        PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);
        pieChart.setTitle("Google Pie Chart", Color.BLACK, 15);
        pieChart.setSize(720, 360);
        pieChart.setThreeD(true);

        model.addAttribute("pieUrl", pieChart.toURLString());

        return "/test/report";
    }

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

    @RequestMapping(value = "/report3", method = RequestMethod.GET)
    public void report3(HttpServletResponse response) {
        response.setContentType("image/png");
        String query = "SELECT endingTime, weight FROM test ORDER BY endingTime ASC";
        JDBCCategoryDataset dataset = null;
        try {
            dataset = new JDBCCategoryDataset(
                    "jdbc:mysql://localhost:3306/lokman",
                    "com.mysql.jdbc.Driver", "root", "root");
            dataset.executeQuery(query);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Yükleme/Üretim Miktarları", "Tarih", "Üretim Miktarı",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        try {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart,
                    1170, 300);
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
