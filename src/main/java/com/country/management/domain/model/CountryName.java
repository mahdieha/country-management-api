package com.country.management.domain.model;

import lombok.Data;

@Data
public class CountryName {

    public CountryName(){}
    public CountryName(String common, String official) {
        this.common = common;
        this.official = official;
    }

    private String common;
    private String official;
}