package xyz.stasiak.excelendpoint;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CovidTotalDeserializer extends StdDeserializer<CovidTotal> {

    protected CovidTotalDeserializer() {
        this(null);
    }

    protected CovidTotalDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CovidTotal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
             throws IOException {
        JsonNode node = jsonParser.getCodec()
                                  .readTree(jsonParser);

        String country = "Unknown";
        LocalDateTime updateDate = LocalDateTime.MIN;
        Integer activeCases = convertToNumber(node.get("Active Cases_text"));
        Integer totalCases = convertToNumber(node.get("Total Cases_text"));
        Integer totalDeaths = convertToNumber(node.get("Total Deaths_text"));
        Integer totalRecovered = convertToNumber(node.get("Total Recovered_text"));
        Integer newCases = convertToNumber(node.get("New Cases_text"));
        Integer newDeaths = convertToNumber(node.get("New Deaths_text"));

        JsonNode countryNode = node.get("Country_text");
        if (countryNode != null) {
            country = countryNode.asText();
        }

        JsonNode updateDateNode = node.get("Last Update");
        if (updateDateNode != null) {
            updateDate = LocalDateTime.parse(updateDateNode.asText(), DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        }

        return new CovidTotal(country, updateDate, activeCases, totalCases, totalDeaths, totalRecovered, newCases,
                              newDeaths);
    }

    private Integer convertToNumber(JsonNode node) {
        if (node == null || "".equals(node.asText())) {
            return -1;
        }
        return Integer.valueOf(node.asText()
                                   .replace(",", ""));
    }
}
