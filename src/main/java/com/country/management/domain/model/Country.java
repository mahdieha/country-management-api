package com.country.management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private CountryName name;
    private String cca3;
    private String region;
    private String subregion;
    private List<String> capital;
    private List<String> borders;
    private double area;
    private int population;

    public double calculatePopulationDensity() {
        return population / area;
    }
}