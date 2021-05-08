package xyz.stasiak.excelendpoint;

import java.io.InputStream;
import java.util.Optional;

interface ResourceProvider {

    Optional<InputStream> getResourceStream();
}
