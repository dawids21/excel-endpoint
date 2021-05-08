package xyz.stasiak.excelendpoint;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class ExcelController {

    @GetMapping(value = "/workbook", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> getWorkbook() throws IOException {
       
        InputStream resourceStream = getResourceStream().orElseThrow(
                 () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
        InputStreamResource resource = new InputStreamResource(resourceStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(getContentDisposition("example-workbook.xlsx"));

        return ResponseEntity.ok()
                             .contentLength(resourceStream.available())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .headers(headers)
                             .body(resource);
    }

    private Optional<InputStream> getResourceStream() {
        return Optional.ofNullable(getClass().getClassLoader()
                                             .getResourceAsStream("static/example-workbook.xlsx"));
    }

    private ContentDisposition getContentDisposition(String filename) {
        return ContentDisposition.builder("inline")
                                 .filename(filename)
                                 .build();
    }

}
