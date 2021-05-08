package xyz.stasiak.excelendpoint;

import java.io.InputStream;
import java.util.Optional;

public class StaticResourceProvider {

    public StaticResourceProvider() {
    }

    Optional<InputStream> getResourceStream() {
        return Optional.ofNullable(getClass().getClassLoader()
                                             .getResourceAsStream("static/example-workbook.xlsx"));
    }
}