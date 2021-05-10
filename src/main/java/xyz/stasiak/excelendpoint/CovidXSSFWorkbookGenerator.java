package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.Workbook;

class CovidXSSFWorkbookGenerator implements WorkbookGenerator {

    private final CovidRestClient covidRestClient;

    CovidXSSFWorkbookGenerator(CovidRestClient covidRestClient) {
        this.covidRestClient = covidRestClient;
    }

    @Override
    public Workbook getWorkbook() {
        return null;
    }
}
