package xyz.stasiak.excelendpoint;

import java.io.InputStream;
import java.util.Optional;

class PoiResourceProvider implements ResourceProvider {

    @Override
    public Optional<InputStream> getResourceStream() {
        return Optional.empty();
    }
}
