package com.country.management.service;

import com.country.management.domain.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryApiService countryApiService;

    @Autowired
    public CountryServiceImpl(CountryApiService countryApiService) {
        this.countryApiService = countryApiService;
    }

    @Override
    public List<Country> getCountriesSortedByPopulationDensity() {

        // Fetch country data from the RestCountries API
        List<Country> countries = countryApiService.getCountries();

        // Sort countries by population density in descending order
        return countries.stream()
                .sorted(Comparator.comparingDouble(Country::calculatePopulationDensity).reversed())
                .toList();
    }


    @Override
    public Country getCountryWithMostBorderingCountriesInDifferentRegion(final String region) {
        // Fetch country data from the RestCountries API
        List<Country> countries = countryApiService.getCountries();

        List<Country> regionCountries = countries.stream()
                .filter(country -> country.getRegion().equalsIgnoreCase(region))
                .toList();


        Country countryWithMostBorderingCountries = null;
        double maxBorderingCountries = 0;


        for (Country regionCountry : regionCountries) {
            if (regionCountry.getBorders() == null || regionCountry.getRegion() == null) {
                continue; // Skip countries with null borders or region
            }

            List<Country> borderWithDifferentRegionCount = regionCountry
                    .getBorders()
                    .stream()
                    .map(code -> findCountryByCode(code, countries))
                    .filter(Objects::nonNull) //To filter out countries that not listed/founded
                    .filter(country -> !regionCountry.getRegion().equalsIgnoreCase(country.getRegion()))
                    .toList();

            if (borderWithDifferentRegionCount.size() > maxBorderingCountries) {
                maxBorderingCountries = borderWithDifferentRegionCount.size();
                countryWithMostBorderingCountries = regionCountry;
            }
        }

        return countryWithMostBorderingCountries;
    }

    // Helper function to find a country by its code
    private Country findCountryByCode(String countryCode, List<Country> countries) {
        for (Country country : countries) {
            if (countryCode.equals(country.getCca3())) {
                return country;
            }
        }
        return null;
    }
}
