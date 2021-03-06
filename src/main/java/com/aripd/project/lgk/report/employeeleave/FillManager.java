package com.aripd.project.lgk.report.employeeleave;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

import com.aripd.project.lgk.model.EmployeeleaveReportModel;
import org.apache.poi.hssf.util.CellReference;

public class FillManager {

    /**
     * Fills the report with content
     *
     * @param worksheet
     * @param startRowIndex starting row offset
     * @param startColIndex starting column offset
     * @param datasource the data source
     */
    public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<EmployeeleaveReportModel> datasource) {
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

            HSSFCell cell0 = row.createCell(startColIndex + 0);
            cell0.setCellValue(datasource.get(i - 2).getDate());
            cell0.setCellStyle(bodyCellStyle);

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            String columnLetter1 = CellReference.convertNumToColString(cell1.getColumnIndex());
            cell1.setCellValue(datasource.get(i - 2).getQualified());
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            String columnLetter2 = CellReference.convertNumToColString(cell2.getColumnIndex());
            cell2.setCellValue(datasource.get(i - 2).getUsed());

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            String columnLetter3 = CellReference.convertNumToColString(cell3.getColumnIndex());
            cell3.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cell3.setCellFormula(columnLetter1 + (row.getRowNum() + 1) + "-" + columnLetter2 + (row.getRowNum() + 1));

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            String columnLetter4 = CellReference.convertNumToColString(cell4.getColumnIndex());

            String preVal = "0";
            if (i > startRowIndex) {
                preVal = columnLetter4 + row.getRowNum();
            }

            cell4.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cell4.setCellFormula(columnLetter3 + (row.getRowNum() + 1) + "+" + preVal);

        }
    }
}
