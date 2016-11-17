package com.huai.assist.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.util.Base64;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Created by liangyh on 11/15/16.
 */
public class ExcelFormUtil {

    public static final String EXCEL_PATH = "temp";

    public static void export(List<List<String>> dyadic, String pathAndName){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathAndName);
            export(dyadic, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void export(List<List<String>> dyadic, OutputStream outputStream){
        if(dyadic == null)throw new NullPointerException("the input data is empty!");

        Workbook workbook = new XSSFWorkbook();
        String safeSheetName = WorkbookUtil.createSafeSheetName("first sheet");
        Sheet sheet = workbook.createSheet(safeSheetName);

        int rowLen = dyadic.size();
        int colLen = dyadic.get(0).size();
        for(int i = 0; i < rowLen; i++){
            //row
            Row row = sheet.createRow(i);
            //cell
            Cell cell = null;
            for(int k = 0; k < colLen; k++){
                cell = row.createCell(k);
                cell.setCellValue(dyadic.get(i).get(k));
            }
        }
        try {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null)outputStream.close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     */
    private static void createCell(Workbook wb, String value, Row row, short column, short halign, short valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }


}












