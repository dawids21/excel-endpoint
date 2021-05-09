package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

class PoiResourceProvider implements ResourceProvider {

    private final XSSFWorkbookGenerator workbookGenerator;

    PoiResourceProvider(XSSFWorkbookGenerator xssfWorkbookGenerator) {
        workbookGenerator = xssfWorkbookGenerator;
    }

    @Override
    public Optional<InputStream> getResourceStream() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Workbook workbook = workbookGenerator.getWorkbook()) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return Optional.of(inputStream);
    }
}
