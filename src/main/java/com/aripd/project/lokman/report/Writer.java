package com.aripd.project.lokman.report;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class Writer {

	protected static Logger logger4J = Logger.getLogger(Writer.class);

	/**
	 * Writes the report to the output stream
	 */
	public static void write(HttpServletResponse response, HSSFSheet worksheet) {

		logger4J.debug("Writing report to the stream");
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			worksheet.getWorkbook().write(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			logger4J.error("Unable to write report to the output stream");
		}
	}

}
