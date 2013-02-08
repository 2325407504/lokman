package com.aripd.project.lokman.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.report.FillManager;
import com.aripd.project.lokman.report.Layouter;
import com.aripd.project.lokman.report.Writer;
import com.aripd.project.lokman.repository.TripRepository;

/**
 * Service for processing Apache POI-based reports
 */
@Service("exportService")
@Transactional
public class ExportService {

	protected static Logger logger = Logger.getLogger(ExportService.class);

	@Autowired
	private TripRepository repository;

	/**
	 * Exports for Excel format. It does the following steps:
	 * 
	 * <pre>
	 * 1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout 
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	public void exportXLS(HttpServletResponse response)
			throws ClassNotFoundException {
		logger.debug("Exporting Excel report");

		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("Trip Report");

		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;

		// 4. Build layout
		// Build title, date, and column headers
		Layouter.buildReport(worksheet, startRowIndex, startColIndex);

		// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex,
				getDatasource());

		// 6. Set the response properties
		String fileName = "SalesReport.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");

		// 7. Write to the output stream
		Writer.write(response, worksheet);

	}

	/**
	 * Retrieves the datasource as as simple Java List
	 */
	private List<Trip> getDatasource() {
		return repository.findAll();
	}

}
