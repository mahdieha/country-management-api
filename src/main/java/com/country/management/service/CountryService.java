package com.country.management.service;

import com.country.management.domain.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {
    /**
     * Get countries sorted by population density in descending order
     *
     * @return List of countries sorted by population density in descending order
     */
    List<Country> getCountriesSortedByPopulationDensity();

    /**
     * Get country with most bordering countries in different region
     *
     * @param region Region to filter countries by
     * @return Country with most bordering countries in different region
     */
    Country getCountryWithMostBorderingCountriesInDifferentRegion(String region);
}