package com.country.management.service;

import com.country.management.domain.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    List<Country> getCountriesSortedByPopulationDensity();

    Country getCountryWithMostBorderingCountriesInDifferentRegion(String region);
}