package com.aripd.project.lokman.report.uatf;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

import com.aripd.project.lokman.domain.Uatf;

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
			int startColIndex, List<Uatf> datasource) {
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
			cell1.setCellValue(datasource.get(i - 2).getForwarding().getWaybillNo());
			cell1.setCellStyle(bodyCellStyle);

			HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(datasource.get(i - 2).getForwarding().getDriver());
			cell2.setCellStyle(bodyCellStyle);

			HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(datasource.get(i - 2).getForwarding().getPlate());
			cell3.setCellStyle(bodyCellStyle);

			HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(datasource.get(i - 2).getForwarding().getStartingAt().toString());
			cell4.setCellStyle(bodyCellStyle);

			HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(datasource.get(i - 2).getForwarding().getEndingAt().toString());
			cell5.setCellStyle(bodyCellStyle);

			HSSFCell cell6 = row.createCell(startColIndex + 5);
			cell6.setCellValue(datasource.get(i - 2).getForwarding().getEndingPoint());
			cell6.setCellStyle(bodyCellStyle);

			HSSFCell cell7 = row.createCell(startColIndex + 6);
			cell7.setCellValue(datasource.get(i - 2).getForwarding().getLoadWeightInTonne());
			cell7.setCellStyle(bodyCellStyle);

			HSSFCell cell8 = row.createCell(startColIndex + 7);
			cell8.setCellValue(datasource.get(i - 2).getForwarding().getShippingCost());
			cell8.setCellStyle(bodyCellStyle);

			HSSFCell cell9 = row.createCell(startColIndex + 8);
			cell9.setCellValue(datasource.get(i - 2).getForwarding().getSubcontractor().getName());
			cell9.setCellStyle(bodyCellStyle);

			HSSFCell cell10 = row.createCell(startColIndex + 9);
			cell10.setCellValue(datasource.get(i - 2).getForwarding().getQuota().getName());
			cell10.setCellStyle(bodyCellStyle);

			HSSFCell cell11 = row.createCell(startColIndex + 10);
			cell11.setCellValue(datasource.get(i - 2).getCode());
			cell11.setCellStyle(bodyCellStyle);

		}
	}

}
