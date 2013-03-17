package com.aripd.project.lgk.report.expense;

import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.aripd.project.lgk.domain.Expense;

public class FillManager {

	private static DateTimeFormatter formatter = DateTimeFormat.forStyle("SS").withLocale(Locale.GERMAN);

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
			int startColIndex, List<Expense> datasource) {
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

			HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(datasource.get(i - 2).getAccount().getCustomer().getFullname());
			cell1.setCellStyle(bodyCellStyle);

			HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(formatter.print(datasource.get(i - 2).getDocumentDate()));
			cell2.setCellStyle(bodyCellStyle);

			HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(datasource.get(i - 2).getDescription());
			cell3.setCellStyle(bodyCellStyle);

			HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(datasource.get(i - 2).getAmount().floatValue());
			cell4.setCellStyle(bodyCellStyle);

		}
	}

}
