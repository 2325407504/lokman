package com.aripd.project.lgk.report.production;

import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.domain.Product;
import com.aripd.project.lgk.domain.Productgroup;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.aripd.project.lgk.domain.Production;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;

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
            cell1.setCellValue(datasource.get(i - 2).getAccount().getUsername());
            cell1.setCellStyle(bodyCellStyle);

            HSSFCell cell2 = row.createCell(startColIndex + 2);
            cell2.setCellValue(datasource.get(i - 2).getRemark());
            cell2.setCellStyle(bodyCellStyle);

            HSSFCell cell3 = row.createCell(startColIndex + 3);
            cell3.setCellValue(datasource.get(i - 2).getFeed());
            cell3.setCellStyle(bodyCellStyle);

            HSSFCell cell4 = row.createCell(startColIndex + 4);
            StringBuilder sb4 = new StringBuilder();

            Iterator<Bigbag> iterator = datasource.get(i - 2).getBigbags().iterator();
            while (iterator.hasNext()) {
                Bigbag bigbag = iterator.next();
                sb4.append(bigbag.getProduct().getName());
                sb4.append(": ");
                sb4.append(bigbag.getWeight());
                if (iterator.hasNext()) {
                    sb4.append("\n");
                }
            }
            cell4.setCellValue(sb4.toString());
            cell4.setCellStyle(bodyCellStyle);

            HSSFCell cell5 = row.createCell(startColIndex + 5);
            Multimap<Productgroup, Product> multimap = ArrayListMultimap.create();
            for (Bigbag bigbag : datasource.get(i - 2).getBigbags()) {
                multimap.put(bigbag.getProduct().getProductgroup(), bigbag.getProduct());
            }
            cell5.setCellValue(multimap.size());
            cell5.setCellStyle(bodyCellStyle);

        }
    }
}
