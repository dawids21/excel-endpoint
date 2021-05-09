package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class XSSFWorkbookGenerator {

    Workbook getWorkbook() {
        return new XSSFWorkbook();
    }
}