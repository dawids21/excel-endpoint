package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Arrays;
import java.util.Comparator;

class CovidXSSFWorkbookGenerator implements WorkbookGenerator {

    private static final String SHEET_NAME = "COVID-19";
    private final CovidRestClient covidRestClient;

    CovidXSSFWorkbookGenerator(CovidRestClient covidRestClient) {
        this.covidRestClient = covidRestClient;
    }

    @Override
    public Workbook getWorkbook() {
        Workbook workbook = new XSSFWorkbook();
        CovidTotal[] covidData = covidRestClient.getTotals();
        Arrays.sort(covidData, 1, covidData.length, Comparator.comparing(CovidTotal::getCountry));
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(5, 3000);
        createTopRow(workbook);
        for (int i = 0; i < covidData.length; i++) {
            createDataRow(workbook, i + 1, covidData[i]);
        }
        return workbook;
    }

    private void createTopRow(Workbook workbook) {
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Country");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Update");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Active");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Total cases");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Total deaths");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Total recovered");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("New cases");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("New deaths");
    }

    private void createDataRow(Workbook workbook, int rowNumber, CovidTotal covidDataEntry) {
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        Row row = sheet.createRow(rowNumber);

        Cell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(covidDataEntry.getCountry());

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat()
                                                .getFormat("dd-mm-yyyy hh:mm"));
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellStyle(dateCellStyle);
        cell.setCellValue(covidDataEntry.getUpdateDate());

        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getActiveCases());

        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getTotalCases());

        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getTotalDeaths());

        cell = row.createCell(5, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getTotalRecovered());

        cell = row.createCell(6, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getNewCases());

        cell = row.createCell(7, CellType.NUMERIC);
        cell.setCellValue(covidDataEntry.getNewDeaths());
    }
}
