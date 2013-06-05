package com.aripd.project.lgk.report.forwarding;

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

import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
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
            int startColIndex, List<Forwarding> datasource) {
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
            cell0.setCellValue(datasource.get(i - 2).getWaybillNo());
            cell0.setCellStyle(bodyCellStyle);

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            cell1.setCellValue(datasource.get(i - 2).getAccount().getUsername());
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getDriver());
            cell2.setCellStyle(bodyCellStyle);

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getPlate());
            cell3.setCellStyle(bodyCellStyle);

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            cell4.setCellValue(formatter.print(datasource.get(i - 2).getStartingTime()));
            cell4.setCellStyle(bodyCellStyle);

            HSSFCell cell5 = row.createCell(startColIndex + 5);
            cell5.setCellValue(formatter.print(datasource.get(i - 2).getEndingTime()));
            cell5.setCellStyle(bodyCellStyle);

            HSSFCell cell6 = row.createCell(startColIndex + 6);
            cell6.setCellValue(datasource.get(i - 2).getEndingPoint());
            cell6.setCellStyle(bodyCellStyle);

            HSSFCell cell7 = row.createCell(startColIndex + 7);
            cell7.setCellValue(datasource.get(i - 2).getLoadWeightInTonne());
            cell7.setCellStyle(bodyCellStyle);

            HSSFCell cell8 = row.createCell(startColIndex + 8);
            cell8.setCellValue(datasource.get(i - 2).getShippingCost().doubleValue());
            cell8.setCellStyle(numericStyle);

            HSSFCell cell9 = row.createCell(startColIndex + 9);
            cell9.setCellValue(datasource.get(i - 2).getSubcontractor().getName());
            cell9.setCellStyle(bodyCellStyle);

            HSSFCell cell10 = row.createCell(startColIndex + 10);
            cell10.setCellValue(datasource.get(i - 2).getQuota().getName());
            cell10.setCellStyle(bodyCellStyle);

            HSSFCell cell11 = row.createCell(startColIndex + 11);
            StringBuilder sUatf = new StringBuilder();
            Set<Uatf> uatfs = datasource.get(i - 2).getUatfs();
            for (Uatf uatf : uatfs) {
                sUatf.append(uatf.getCode());
                sUatf.append("\n");
            }
            cell11.setCellValue(sUatf.toString());
            cell11.setCellStyle(bodyCellStyle);

        }
    }
}
