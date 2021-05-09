package xyz.stasiak.excelendpoint;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

class WorkbookResourceProvider implements ResourceProvider {

    private final WorkbookGenerator workbookGenerator;

    WorkbookResourceProvider(WorkbookGenerator workbookGenerator) {
        this.workbookGenerator = workbookGenerator;
    }

    @Override
    public Optional<ByteArrayResource> getResource() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Workbook workbook = workbookGenerator.getWorkbook()) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(new ByteArrayResource(outputStream.toByteArray()));
    }
}
