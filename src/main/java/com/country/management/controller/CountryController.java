package com.country.management.controller;

import com.country.management.domain.model.Country;
import com.country.management.domain.validator.ValidRegion;
import com.country.management.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@Validated
public class CountryController {
    private final CountryService countryService;

    @Operation(summary = "Get list of countries sorted by density", description = "Returns sorted list of countries by population density in descending order.")
    @GetMapping("/population-density")
    public List<Country> getCountriesSortedByPopulationDensity() {
        return countryService.getCountriesSortedByPopulationDensity();
    }

    @Operation(summary = "Get most bordering country with different region", description = "Returns a country in specifiec region containing the most bordering countries of a different region.")
    @GetMapping("/most-bordering/{region}")
    public Country getCountryWithMostBorderingCountriesInDifferentRegion(
              @PathVariable @ValidRegion @Parameter(name = "region", example = "asia") String region
    ) {
        return countryService.getCountryWithMostBorderingCountriesInDifferentRegion(region);
    }
}