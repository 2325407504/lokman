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
import org.apache.poi.hssf.usermodel.HSSFDataFormat;

public class FillManager {

    private static DateTimeFormatter formatter = DateTimeFormat.forStyle("MS").withLocale(Locale.GERMAN);

    /**
     * Fills the report with content
     *
     * @param worksheet
     * @param startRowIndex starting row offset
     * @param startColIndex starting column offset
     * @param datasource the data source
     */
    public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Expense> datasource) {
        // Row offset
        startRowIndex += 2;

        // Create cell style for the body
        HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
        bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        bodyCellStyle.setWrapText(true);

        HSSFCellStyle numericStyle = worksheet.getWorkbook().createCellStyle();
        numericStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

        // Create body
        for (int i = startRowIndex; i + startRowIndex - 2 < datasource.size() + 2; i++) {
            // Create a new row
            HSSFRow row = worksheet.createRow((short) i + 1);

            HSSFCell cell0 = row.createCell(startColIndex + 0);
            cell0.setCellValue(datasource.get(i - 2).getAccount().getUsername());
            cell0.setCellStyle(bodyCellStyle);

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            cell1.setCellValue(datasource.get(i - 2).getExpensetype().getCode());
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getDocumentDate().toDate());

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getCompany());
            cell3.setCellStyle(bodyCellStyle);

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            cell4.setCellValue(datasource.get(i - 2).getDescription());
            cell4.setCellStyle(bodyCellStyle);

            HSSFCell cell5 = row.createCell(startColIndex + 5);
            cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell5.setCellValue(datasource.get(i - 2).getAmount().doubleValue());
            cell5.setCellStyle(numericStyle);

        }
    }
}
