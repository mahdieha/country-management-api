package com.country.management.service;


import static org.mockito.Mockito.*;

import com.country.management.domain.model.Country;
import com.country.management.domain.model.CountryName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    private CountryApiService countryApiService;

    @InjectMocks
    private CountryServiceImpl countryService;


    @BeforeEach
    void setUp(){
        List<Country> mockCountries = new ArrayList<>();
        mockCountries.add(createCountry("Country1", "Region1", "CO1", Arrays.asList("CO2", "CO4", "CO5"), 500, 100));
        mockCountries.add(createCountry("Country2", "Region2", "CO2", Arrays.asList("CO3", "CO4", "CO5"), 1000, 100));
        mockCountries.add(createCountry("Country3", "Region3", "CO3", Arrays.asList("CO2", "CO4"), 400, 100));
        mockCountries.add(createCountry("Country4", "Region1", "CO4", Arrays.asList("CO1", "CO2", "CO3"), 300, 100));
        mockCountries.add(createCountry("Country5", "Region1", "CO5", Arrays.asList("CO1", "CO2"), 200, 100));
        when(countryApiService.getCountries()).thenReturn(mockCountries);
    }


    @Test
    void testGetCountriesSortedByPopulationDensity() {
        List<Country> result = countryService.getCountriesSortedByPopulationDensity();

        assertEquals(5, result.size());
        assertEquals("Country2", result.get(0).getName().getCommon());
        assertEquals("Country1", result.get(1).getName().getCommon());
        assertEquals("Country3", result.get(2).getName().getCommon());
        assertEquals("Country4", result.get(3).getName().getCommon());
        assertEquals("Country5", result.get(4).getName().getCommon());
    }

    @Test
    void testGetCountryWithMostBorderingCountriesInDifferentRegion() {
        String region = "Region1";
        Country result = countryService.getCountryWithMostBorderingCountriesInDifferentRegion(region);

        assertEquals("Country4", result.getName().getCommon());
    }

    private Country createCountry(String name, String region, String cca3, List<String> borders, int population, double area) {
        Country country = new Country();
        country.setName(new CountryName(name, name));
        country.setRegion(region);
        country.setCca3(cca3);
        country.setBorders(borders);
        country.setPopulation(population);
        country.setArea(area);
        return country;
    }
}
