package com.aripd.project.lokman.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aripd.project.lokman.service.ExportService;

@Controller
@RequestMapping("/fms")
public class ExportController {

	protected static Logger logger = Logger.getLogger(ExportController.class);

	@Resource(name = "exportService")
	private ExportService exportService;

	/**
	 * Exports the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 */
	@RequestMapping(value = "/export/xls", method = RequestMethod.GET)
	public void getXLS(HttpServletResponse response, Model model)
			throws ClassNotFoundException {
		logger.debug("Received request to export report as an XLS");

		// Delegate to exportService. Make sure to pass an instance of
		// HttpServletResponse
		exportService.exportXLS(response);
	}
}