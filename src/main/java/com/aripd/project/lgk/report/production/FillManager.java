package com.aripd.project.lgk.report.production;

import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.domain.Compensation;
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

import com.aripd.project.lgk.domain.Production;
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
    public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Production> datasource) {
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
            cell0.setCellValue(formatter.print(datasource.get(i - 2).getShiftdate()));
            cell0.setCellStyle(bodyCellStyle);

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            cell1.setCellValue(datasource.get(i - 2).getShift().getName());
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getAccount().getUsername());
            cell2.setCellStyle(bodyCellStyle);

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getRemark());
            cell3.setCellStyle(bodyCellStyle);

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            StringBuilder sb = new StringBuilder();
            Set<Bigbag> bigbags = datasource.get(i - 2).getBigbags();
            for (Bigbag bigbag : bigbags) {
                sb.append(bigbag.getWeight());
                sb.append("\n");
            }
            cell4.setCellValue(sb.toString());
            cell4.setCellStyle(bodyCellStyle);

            int dyn = 1;
            List<Compensation> compensations = datasource.get(i - 2).getCompensations();
            for (Compensation compensation : compensations) {
                HSSFCell cellc = row.createCell(startColIndex + 4 + dyn);
                cellc.setCellValue(compensation.getVal());
                cellc.setCellStyle(bodyCellStyle);
                dyn++;
            }

        }
    }
}
