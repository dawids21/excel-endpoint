package xyz.stasiak.excelendpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

class CovidRestClient {

    @Value("${rapidapi.covid.url}")
    private String covidUrl;
    @Value("${rapidapi.key.name}")
    private String apiKeyName;
    @Value("${rapidapi.key.value}")
    private String apiKeyValue;
    @Value("${rapidapi.host.name}")
    private String hostName;
    @Value("${rapidapi.host.covid.value}")
    private String hostValue;

    RestTemplate restTemplate;

    CovidRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    CovidTotal[] getTotals() {
        CovidTotal[] total = null;
        try {
            URI uri;
            uri = new URI(covidUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set(apiKeyName, apiKeyValue);
            headers.set(hostName, hostValue);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<CovidTotal[]> totalEntity =
                     restTemplate.exchange(uri, HttpMethod.GET, request, CovidTotal[].class);
            total = totalEntity.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return total;
    }
}
