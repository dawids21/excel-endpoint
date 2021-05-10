package xyz.stasiak.excelendpoint;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/excel")
class ExcelController {

    @GetMapping(value = "/empty/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = "Get Excel file with specified filename")
    ResponseEntity<Resource> getEmptyWorkbook(@ApiParam(defaultValue = "spreadsheet") @PathVariable String filename) {

        ByteArrayResource resource = getResource(Type.EMPTY);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(getContentDisposition(filename));

        return ResponseEntity.ok()
                             .contentLength(resource.contentLength())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .headers(headers)
                             .body(resource);
    }

    private ContentDisposition getContentDisposition(String filename) {
        String filenameWithExtension = filename + (filename.endsWith(".xlsx") ? "" : ".xlsx");
        return ContentDisposition.builder("attachment")
                                 .filename(filenameWithExtension)
                                 .build();
    }

    private enum Type {
        STATIC, EMPTY
    }

    private ByteArrayResource getResource(Type type) {
        ResourceProvider resourceProvider;
        if (type == Type.EMPTY) {
            resourceProvider = new EmptyWorkbookResourceProvider(new EmptyXSSFWorkbookGenerator());
        } else {
            resourceProvider = new StaticResourceProvider();
        }

        return resourceProvider.getResource()
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                                                              "Error while generating Excel file"));
    }
}
