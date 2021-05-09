package xyz.stasiak.excelendpoint;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

class StaticResourceProvider implements ResourceProvider {

    @Override
    public Optional<ByteArrayResource> getResource() {
        byte[] bytes;
        try {
            InputStream resourceStream = getClass().getClassLoader()
                                                   .getResourceAsStream("static/example-workbook.xlsx");
            bytes = Objects.requireNonNull(resourceStream)
                           .readAllBytes();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(new ByteArrayResource(bytes));
    }
}