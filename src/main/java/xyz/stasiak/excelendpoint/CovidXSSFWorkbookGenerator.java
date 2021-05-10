package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDate;

class CovidXSSFWorkbookGenerator implements WorkbookGenerator {

    private final CovidRestClient covidRestClient;

    CovidXSSFWorkbookGenerator(CovidRestClient covidRestClient) {
        this.covidRestClient = covidRestClient;
    }

    @Override
    public Workbook getWorkbook() {
        Workbook workbook = new XSSFWorkbook();
        CovidTotal[] covidData = covidRestClient.getTotals();
        Sheet sheet = workbook.createSheet("COVID-19");
        createTopRow(sheet);
        for (int i = 0; i < covidData.length; i++) {
            createDataRow(sheet, i + 1, covidData[i]);
        }
        return workbook;
    }

    private void createTopRow(Sheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Date");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Active");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Confirmed");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Critical");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Deaths");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Recovered");
    }

    private void createDataRow(Sheet sheet, int rowNumber, CovidTotal covidDataEntry) {
        Row row = sheet.createRow(rowNumber);

        Cell cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue(LocalDate.parse(covidDataEntry.getDate()));

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getActive());

        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getConfirmed());

        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getCritical());

        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getDeaths());

        cell = row.createCell(5, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getRecovered());
    }
}
