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
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
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

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            cell1.setCellValue(datasource.get(i - 2).getAccount().getUsername());
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getRemark());
            cell2.setCellStyle(bodyCellStyle);

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getFeed());
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
                HSSFCell cellc1 = row.createCell(startColIndex + 4 + dyn);
                cellc1.setCellValue(compensation.getVal());
                cellc1.setCellStyle(numericStyle);

                dyn++;

                String preVal = "0";
                String columnLetter = CellReference.convertNumToColString(cellc1.getColumnIndex());
                String curVal = columnLetter + (row.getRowNum() + 1);
                if (i > startRowIndex) {
                    preVal = columnLetter + row.getRowNum();
                }

                HSSFCell cellc2 = row.createCell(startColIndex + 4 + dyn);
                cellc2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                cellc2.setCellFormula("(" + curVal + "-" + preVal + ")*2070");
                cellc2.setCellStyle(numericStyle);

                dyn++;
            }

            HSSFCell cellc5 = row.createCell(startColIndex + 4 + dyn + 0);
            cellc5.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cellc5.setCellFormula("O" + (row.getRowNum() + 1) + "/G" + (row.getRowNum() + 1));
            cellc5.setCellStyle(percentageStyle);

            HSSFCell cellc6 = row.createCell(startColIndex + 4 + dyn + 1);
            cellc6.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cellc6.setCellFormula("Q" + (row.getRowNum() + 1) + "/G" + (row.getRowNum() + 1));
            cellc6.setCellStyle(percentageStyle);

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
            //CellRangeAddress[] my_data_range1 = {CellRangeAddress.valueOf(CellReference.convertNumToColString(cellc5.getColumnIndex()) + startRowIndex + ":" + CellReference.convertNumToColString(cellc5.getColumnIndex()) + i)};
            //CellRangeAddress[] my_data_range2 = {CellRangeAddress.valueOf(CellReference.convertNumToColString(cellc6.getColumnIndex()) + startRowIndex + ":" + CellReference.convertNumToColString(cellc6.getColumnIndex()) + i)};
            CellRangeAddress[] range1 = {CellRangeAddress.valueOf("R1:R1000")};
            CellRangeAddress[] range2 = {CellRangeAddress.valueOf("S1:S1000")};
            layer.addConditionalFormatting(range1, rule1);
            layer.addConditionalFormatting(range2, rule2);

        }
    }
}
