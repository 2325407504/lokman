package com.aripd.project.lgk.report.waybill;

import com.aripd.project.lgk.domain.Outgoing;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.aripd.project.lgk.domain.Waybill;
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
    public static void fillReport(HSSFSheet worksheet, int startRowIndex,
            int startColIndex, List<Waybill> datasource) {
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
            cell1.setCellValue(formatter.print(datasource.get(i - 2).getDocumentDate()));
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getDocumentNo());
            cell2.setCellStyle(bodyCellStyle);

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getCompany());
            cell3.setCellStyle(bodyCellStyle);

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            cell4.setCellValue(datasource.get(i - 2).getDriver());
            cell4.setCellStyle(bodyCellStyle);

            HSSFCell cell5 = row.createCell(startColIndex + 5);
            cell5.setCellValue(datasource.get(i - 2).getPlate());
            cell5.setCellStyle(bodyCellStyle);

            HSSFCell cell6 = row.createCell(startColIndex + 6);
            StringBuilder sb = new StringBuilder();
            Set<Outgoing> outgoings = datasource.get(i - 2).getOutgoings();
            for (Outgoing outgoing : outgoings) {
                sb.append(outgoing.getProduct().getName() + ',' + outgoing.getRemark() + ',' + outgoing.getWeight());
                sb.append("\n");
            }
            cell6.setCellValue(sb.toString());
            cell6.setCellStyle(bodyCellStyle);

            HSSFCell cell7 = row.createCell(startColIndex + 7);
            cell7.setCellValue(datasource.get(i - 2).getInvoiceCompany());
            cell7.setCellStyle(bodyCellStyle);

            HSSFCell cell8 = row.createCell(startColIndex + 8);
            cell8.setCellValue(datasource.get(i - 2).getInvoiceNo());
            cell8.setCellStyle(bodyCellStyle);

            HSSFCell cell9 = row.createCell(startColIndex + 9);
            cell9.setCellValue(formatter.print(datasource.get(i - 2).getInvoiceDate()));
            cell9.setCellStyle(bodyCellStyle);

            HSSFCell cell10 = row.createCell(startColIndex + 10);
            cell10.setCellValue(datasource.get(i - 2).getInvoiceAmount().doubleValue());
            cell10.setCellStyle(numericStyle);

        }
    }
}
