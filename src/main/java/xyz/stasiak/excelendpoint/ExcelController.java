package xyz.stasiak.excelendpoint;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    private final ResourceProvider resourceProvider;

    ExcelController(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @GetMapping(value = "/excel/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = "Get Excel file with specified filename")
    ResponseEntity<Resource> getWorkbook(@ApiParam(defaultValue = "spreadsheet") @PathVariable String filename)
             throws IOException {

        InputStream resourceStream = resourceProvider.getResourceStream()
                                                     .orElseThrow(
                                                              () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                                "File not found"));
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
        String filenameWithExtension = filename + (filename.endsWith(".xlsx") ? "" : ".xlsx");
        return ContentDisposition.builder("inline")
                                 .filename(filenameWithExtension)
                                 .build();
    }

}
