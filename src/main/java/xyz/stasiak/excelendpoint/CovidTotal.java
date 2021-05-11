package xyz.stasiak.excelendpoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

@JsonDeserialize(using = CovidTotalDeserializer.class)
class CovidTotal {

    private String country;
    private LocalDateTime updateDate;
    private Integer activeCases;
    private Integer totalCases;
    private Integer totalDeaths;
    private Integer totalRecovered;
    private Integer newCases;
    private Integer newDeaths;

    CovidTotal(String country, LocalDateTime updateDate, Integer activeCases, Integer totalCases, Integer totalDeaths,
               Integer totalRecovered, Integer newCases, Integer newDeaths) {
        this.country = country;
        this.updateDate = updateDate;
        this.activeCases = activeCases;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    LocalDateTime getUpdateDate() {
        return updateDate;
    }

    void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    Integer getActiveCases() {
        return activeCases;
    }

    void setActiveCases(Integer activeCases) {
        this.activeCases = activeCases;
    }

    Integer getTotalCases() {
        return totalCases;
    }

    void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    Integer getTotalDeaths() {
        return totalDeaths;
    }

    void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    Integer getTotalRecovered() {
        return totalRecovered;
    }

    void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    Integer getNewCases() {
        return newCases;
    }

    void setNewCases(Integer newCases) {
        this.newCases = newCases;
    }

    Integer getNewDeaths() {
        return newDeaths;
    }

    void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }
}
