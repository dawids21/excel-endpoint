package xyz.stasiak.excelendpoint;

import java.io.InputStream;
import java.util.Optional;

class StaticResourceProvider implements ResourceProvider {

    @Override
    public Optional<InputStream> getResourceStream() {
        return Optional.ofNullable(getClass().getClassLoader()
                                             .getResourceAsStream("static/example-workbook.xlsx"));
    }
}