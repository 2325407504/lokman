package com.aripd.project.lgk.report.employeeworkinghour;

import com.aripd.project.lgk.domain.Employeeworkinghour;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

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
    public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Employeeworkinghour> datasource) {
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
            String columnLetter0 = CellReference.convertNumToColString(cell0.getColumnIndex());
            cell0.setCellValue(datasource.get(i - 2).getStartingTime().toDate());

            HSSFCell cell1 = row.createCell(startColIndex + 1);
            String columnLetter1 = CellReference.convertNumToColString(cell1.getColumnIndex());
            cell1.setCellValue(datasource.get(i - 2).getEndingTime().toDate());

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            String columnLetter2 = CellReference.convertNumToColString(cell2.getColumnIndex());
            cell2.setCellValue(datasource.get(i - 2).getNofWorkhours());

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            String columnLetter3 = CellReference.convertNumToColString(cell3.getColumnIndex());

            String preVal = "0";
            if (i > startRowIndex) {
                preVal = columnLetter3 + row.getRowNum();
            }

            cell3.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            cell3.setCellFormula(columnLetter2 + (row.getRowNum() + 1) + "+" + preVal);

        }
    }
}
