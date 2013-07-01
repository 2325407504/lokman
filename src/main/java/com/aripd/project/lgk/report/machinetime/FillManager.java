package com.aripd.project.lgk.report.machinetime;

import com.aripd.project.lgk.domain.Machinetime;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.aripd.project.lgk.domain.Production;
import org.apache.poi.hssf.record.CFRuleRecord.ComparisonOperator;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingRule;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFontFormatting;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFSheetConditionalFormatting;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

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

        HSSFCellStyle percentageStyle = worksheet.getWorkbook().createCellStyle();
        percentageStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));

        // Create body
        for (int i = startRowIndex; i + startRowIndex - 2 < datasource.size() + 2; i++) {
            // Create a new row
            HSSFRow row = worksheet.createRow((short) i + 1);

            HSSFCell cell0 = row.createCell(startColIndex + 0);
            cell0.setCellValue(formatter.print(datasource.get(i - 2).getShiftdate()));
            cell0.setCellStyle(bodyCellStyle);

            int dyn = 1;
            List<Machinetime> machinetimes = datasource.get(i - 2).getMachinetimes();
            for (Machinetime machinetime : machinetimes) {
                HSSFCell cellc1 = row.createCell(startColIndex + 0 + dyn);
                cellc1.setCellValue(machinetime.getVal());
                cellc1.setCellStyle(numericStyle);

                dyn++;

                String preVal = "0";
                String columnLetter = CellReference.convertNumToColString(cellc1.getColumnIndex());
                String curVal = columnLetter + (row.getRowNum() + 1);
                if (i > startRowIndex) {
                    preVal = columnLetter + row.getRowNum();
                }

                HSSFCell cellc2 = row.createCell(startColIndex + 0 + dyn);
                cellc2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                cellc2.setCellFormula(curVal + "-" + preVal);
                cellc2.setCellStyle(numericStyle);

                dyn++;
            }

        }
    }
}
