package com.aripd.project.lgk.report.compensation;

import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Production;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class Layouter {

    protected static Logger logger = Logger.getLogger(Layouter.class);

    /**
     * Builds the report layout.
     * <p>
     * This doesn't have any data yet. This is your template.
     */
    public static void buildReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Production> datasource) {
        // Build the title and date headers
        buildTitle(worksheet, startRowIndex, startColIndex);
        // Build the column headers
        buildHeaders(worksheet, startRowIndex, startColIndex, datasource);
    }

    /**
     * Builds the report title and the date header
     *
     * @param worksheet
     * @param startRowIndex starting row offset
     * @param startColIndex starting column offset
     */
    public static void buildTitle(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
        // Create font style for the report title
        Font fontTitle = worksheet.getWorkbook().createFont();
        fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fontTitle.setFontHeight((short) 280);

        // Create cell style for the report title
        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setWrapText(true);
        cellStyleTitle.setFont(fontTitle);

        // Create report title
        HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
        rowTitle.setHeight((short) 500);
        HSSFCell cellTitle = rowTitle.createCell(startColIndex);
        cellTitle.setCellValue("Compensation Report");
        cellTitle.setCellStyle(cellStyleTitle);

        // Create merged region for the report title
        worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));

        // Create date header
        HSSFRow dateTitle = worksheet.createRow((short) startRowIndex + 1);
        HSSFCell cellDate = dateTitle.createCell(startColIndex);
        cellDate.setCellValue("This report was generated at " + new Date());
    }

    /**
     * Builds the column headers
     *
     * @param worksheet
     * @param startRowIndex starting row offset
     * @param startColIndex starting column offset
     */
    public static void buildHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Production> datasource) {
        // Create font style for the headers
        Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
        HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
        headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setFont(font);
        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        // Create the column headers
        HSSFRow rowHeader = worksheet.createRow((short) startRowIndex + 2);
        rowHeader.setHeight((short) 500);

        HSSFCell cell0 = rowHeader.createCell(startColIndex + 0);
        cell0.setCellValue("Shift");
        cell0.setCellStyle(headerCellStyle);

        int dyn = 1;
        List<Compensation> compensations = datasource.get(0).getCompensations();
        for (Compensation compensation : compensations) {
            HSSFCell cellc1 = rowHeader.createCell(startColIndex + 0 + dyn);
            cellc1.setCellValue(compensation.getElectricmeter().getName());
            cellc1.setCellStyle(headerCellStyle);

            dyn++;

            HSSFCell cellc2 = rowHeader.createCell(startColIndex + 0 + dyn);
            cellc2.setCellValue(compensation.getElectricmeter().getName() + "\nkW·h");
            cellc2.setCellStyle(headerCellStyle);

            dyn++;
        }

        HSSFCell cell5 = rowHeader.createCell(startColIndex + 0 + dyn + 0);
        cell5.setCellValue("580 İnd/Reak");
        cell5.setCellStyle(headerCellStyle);

        HSSFCell cell6 = rowHeader.createCell(startColIndex + 0 + dyn + 1);
        cell6.setCellValue("880 Kap/Reak");
        cell6.setCellStyle(headerCellStyle);

    }
}
