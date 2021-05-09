package xyz.stasiak.excelendpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ExcelEndpointConfig {

    @Bean
    ResourceProvider resourceProvider() {
        return new PoiResourceProvider(new XSSFWorkbookGenerator());
    }
}
