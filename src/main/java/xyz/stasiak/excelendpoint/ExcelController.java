package xyz.stasiak.excelendpoint;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
class ExcelController {

    @GetMapping(value = "/workbook", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> getWorkbook() throws IOException {
        InputStream resourceStream = getClass().getClassLoader()
                                               .getResourceAsStream("static/example-workbook.xlsx");
        InputStreamResource resource = new InputStreamResource(resourceStream);
        return ResponseEntity.ok()
                             .contentLength(resourceStream.available())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .body(resource);
    }

}
