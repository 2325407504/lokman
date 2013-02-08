package com.aripd.project.lokman.report;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

import com.aripd.project.lokman.domain.Trip;

public class FillManager {

	/**
	 * Fills the report with content
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 * @param datasource
	 *            the data source
	 */
	public static void fillReport(HSSFSheet worksheet, int startRowIndex,
			int startColIndex, List<Trip> datasource) {
		// Row offset
		startRowIndex += 2;

		// Create cell style for the body
		HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellStyle.setWrapText(true);

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < datasource.size() + 2; i++) {
			// Create a new row
			HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the id value
			HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(datasource.get(i - 2).getId());
			cell1.setCellStyle(bodyCellStyle);

			// Retrieve the brand value
			HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(datasource.get(i - 2).getDriver().getFullname());
			cell2.setCellStyle(bodyCellStyle);

			// Retrieve the model value
			HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(datasource.get(i - 2).getTruck().getPlate());
			cell3.setCellStyle(bodyCellStyle);

			// Retrieve the maximum power value
			HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(datasource.get(i - 2).getLoadWeightInTonne());
			cell4.setCellStyle(bodyCellStyle);

		}
	}

}
