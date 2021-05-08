package xyz.stasiak.excelendpoint;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
class ExcelController {

    private final StaticResourceProvider staticResourceProvider = new StaticResourceProvider();

    @GetMapping(value = "/excel/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> getWorkbook(@PathVariable String filename) throws IOException {

        InputStream resourceStream = staticResourceProvider.getResourceStream()
                                                           .orElseThrow(() -> new ResponseStatusException(
                                                                    HttpStatus.NOT_FOUND, "File not found"));
        InputStreamResource resource = new InputStreamResource(resourceStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(getContentDisposition(filename));

        return ResponseEntity.ok()
                             .contentLength(resourceStream.available())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .headers(headers)
                             .body(resource);
    }

    private ContentDisposition getContentDisposition(String filename) {
        return ContentDisposition.builder("inline")
                                 .filename(filename)
                                 .build();
    }

}
