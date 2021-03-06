package com.aripd.project.lgk.report.compensation;

import com.aripd.project.lgk.domain.Compensation;
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
            List<Compensation> compensations = datasource.get(i - 2).getCompensations();
            for (Compensation compensation : compensations) {
                HSSFCell cellc1 = row.createCell(startColIndex + 0 + dyn);
                cellc1.setCellValue(Integer.valueOf(compensation.getVal()));
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
                cellc2.setCellFormula("(" + curVal + "-" + preVal + ")*2070");
                cellc2.setCellStyle(numericStyle);

                dyn++;
            }

            HSSFCell cellc5 = row.createCell(startColIndex + 0 + dyn + 0);
            cellc5.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cellc5.setCellFormula("K" + (row.getRowNum() + 1) + "/C" + (row.getRowNum() + 1));
            cellc5.setCellStyle(percentageStyle);
            String cellc5ColumnLetter = CellReference.convertNumToColString(cellc5.getColumnIndex());

            HSSFCell cellc6 = row.createCell(startColIndex + 0 + dyn + 1);
            cellc6.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cellc6.setCellFormula("M" + (row.getRowNum() + 1) + "/C" + (row.getRowNum() + 1));
            cellc6.setCellStyle(percentageStyle);
            String cellc6ColumnLetter = CellReference.convertNumToColString(cellc6.getColumnIndex());

            /**
             * ******** CONDITIONAL FORMATTING *********
             */
            /* Access conditional formatting facet layer */
            HSSFSheetConditionalFormatting layer = worksheet.getSheetConditionalFormatting();

            HSSFConditionalFormattingRule rule1 = layer.createConditionalFormattingRule(ComparisonOperator.GE, ".2");
            HSSFFontFormatting font_pattern1 = rule1.createFontFormatting();
            font_pattern1.setFontColorIndex(IndexedColors.WHITE.getIndex());
            HSSFPatternFormatting fill_pattern1 = rule1.createPatternFormatting();
            fill_pattern1.setFillBackgroundColor(IndexedColors.RED.index);

            HSSFConditionalFormattingRule rule2 = layer.createConditionalFormattingRule(ComparisonOperator.GE, ".15");
            HSSFFontFormatting font_pattern2 = rule2.createFontFormatting();
            font_pattern2.setFontColorIndex(IndexedColors.WHITE.getIndex());
            HSSFPatternFormatting fill_pattern2 = rule2.createPatternFormatting();
            fill_pattern2.setFillBackgroundColor(IndexedColors.RED.index);

            /* OK, we have defined mutliple rules. Time to attach two rules to same range. We create an array of rules now */
            //ConditionalFormattingRule[] rules = {rule1, rule2};
            //CellRangeAddress[] range = {CellRangeAddress.valueOf("R1:S1000")};
            //layer.addConditionalFormatting(range, rules);

            /* Create a Cell Range Address */
            CellRangeAddress[] range1 = {CellRangeAddress.valueOf(cellc5ColumnLetter + (startRowIndex + 2) + ":" + cellc5ColumnLetter + (datasource.size() + startRowIndex + 1))};
            CellRangeAddress[] range2 = {CellRangeAddress.valueOf(cellc6ColumnLetter + (startRowIndex + 2) + ":" + cellc6ColumnLetter + (datasource.size() + startRowIndex + 1))};
            layer.addConditionalFormatting(range1, rule1);
            layer.addConditionalFormatting(range2, rule2);

        }
    }
}
