package xyz.stasiak.excelendpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.Collections;

@Configuration
class ExcelEndpointConfig {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.basePackage(
                                                               "xyz.stasiak.excelendpoint"))
                                                      .paths(PathSelectors.ant("/api/**"))
                                                      .build()
                                                      .pathMapping("/")
                                                      .genericModelSubstitutes(ResponseEntity.class)
                                                      .useDefaultResponseMessages(false)
                                                      .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Excel API", "API for generating Excel files", "1.0", "",
                           new Contact("Dawid Stasiak", "", "dawid.stasiak21@gmail.com"), "", "",
                           Collections.emptyList());
    }

    @Bean
    UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                                     .deepLinking(true)
                                     .displayOperationId(false)
                                     .defaultModelsExpandDepth(-1)
                                     .defaultModelExpandDepth(1)
                                     .defaultModelRendering(ModelRendering.EXAMPLE)
                                     .displayRequestDuration(false)
                                     .docExpansion(DocExpansion.NONE)
                                     .filter(false)
                                     .maxDisplayedTags(null)
                                     .operationsSorter(OperationsSorter.ALPHA)
                                     .showExtensions(false)
                                     .showCommonExtensions(false)
                                     .tagsSorter(TagsSorter.ALPHA)
                                     .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                                     .validatorUrl(null)
                                     .build();
    }
}
