package com.aripd.project.lgk.report.outgoing;

import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.aripd.project.lgk.domain.Outgoing;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;

public class FillManager {

    private static DateTimeFormatter formatter = DateTimeFormat.forStyle("SS").withLocale(Locale.GERMAN);

    /**
     * Fills the report with content
     *
     * @param worksheet
     * @param startRowIndex starting row offset
     * @param startColIndex starting column offset
     * @param datasource the data source
     */
    public static void fillReport(HSSFSheet worksheet, int startRowIndex,int startColIndex, List<Outgoing> datasource) {
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
            cell0.setCellValue(datasource.get(i - 2).getWaybill().getAccount().getUsername());
            cell0.setCellStyle(bodyCellStyle);

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            cell1.setCellValue(formatter.print(datasource.get(i - 2).getWaybill().getDocumentDate()));
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getWaybill().getDocumentNo());
            cell2.setCellStyle(bodyCellStyle);

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getWaybill().getCompany());
            cell3.setCellStyle(bodyCellStyle);

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            cell4.setCellValue(datasource.get(i - 2).getWaybill().getDriver());
            cell4.setCellStyle(bodyCellStyle);

            HSSFCell cell5 = row.createCell(startColIndex + 5);
            cell5.setCellValue(datasource.get(i - 2).getWaybill().getPlate());
            cell5.setCellStyle(bodyCellStyle);

            HSSFCell cell6 = row.createCell(startColIndex + 6);
            cell6.setCellValue(datasource.get(i - 2).getProduct().getName());
            cell6.setCellStyle(bodyCellStyle);

            HSSFCell cell7 = row.createCell(startColIndex + 7);
            cell7.setCellValue(datasource.get(i - 2).getWeight());
            cell7.setCellStyle(bodyCellStyle);

            HSSFCell cell8 = row.createCell(startColIndex + 8);
            cell8.setCellValue(datasource.get(i - 2).getRemark());
            cell8.setCellStyle(bodyCellStyle);

            HSSFCell cell9 = row.createCell(startColIndex + 9);
            cell9.setCellValue(formatter.print(datasource.get(i - 2).getWaybill().getInvoice().getDocumentDate()));
            cell9.setCellStyle(bodyCellStyle);

            HSSFCell cell10 = row.createCell(startColIndex + 10);
            cell10.setCellValue(datasource.get(i - 2).getWaybill().getInvoice().getDocumentNo());
            cell10.setCellStyle(bodyCellStyle);

            HSSFCell cell11 = row.createCell(startColIndex + 11);
            cell11.setCellValue(datasource.get(i - 2).getWaybill().getInvoice().getCustomer().getName());
            cell11.setCellStyle(bodyCellStyle);

            HSSFCell cell12 = row.createCell(startColIndex + 12);
            cell12.setCellValue(datasource.get(i - 2).getWaybill().getInvoice().getAmount().doubleValue());
            cell12.setCellStyle(numericStyle);

        }
    }
}
