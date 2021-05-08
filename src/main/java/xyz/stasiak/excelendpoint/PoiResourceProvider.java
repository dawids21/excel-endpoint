package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

class PoiResourceProvider implements ResourceProvider {

    @Override
    public Optional<InputStream> getResourceStream() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Workbook workbook = new XSSFWorkbook()) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return Optional.of(inputStream);
    }
}
