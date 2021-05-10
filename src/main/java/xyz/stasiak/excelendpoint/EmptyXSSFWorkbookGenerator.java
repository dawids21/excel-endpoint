package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class EmptyXSSFWorkbookGenerator implements WorkbookGenerator {

    @Override
    public Workbook getWorkbook() {
        return new XSSFWorkbook();
    }
}