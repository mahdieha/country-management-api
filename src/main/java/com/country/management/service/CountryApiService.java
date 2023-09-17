package com.country.management.service;

import com.country.management.domain.constants.ExceptionMessage;
import com.country.management.domain.exception.CountryApiException;
import com.country.management.domain.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CountryApiService {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CountryApiService(RestTemplateBuilder restTemplateBuilder, @Value("${restcountries.api.base-url}") String apiUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiUrl = apiUrl;
    }

    @Cacheable("countries")
    public List<Country> getCountries() {
        ResponseEntity<Country[]> response;
        try {
            response = restTemplate.getForEntity(apiUrl, Country[].class);
        } catch (Exception e) {
            throw new CountryApiException(ExceptionMessage.FAILED_FETCH_COUNTRY, e);
        }

        validateResponse(response);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    private void validateResponse(ResponseEntity<Country[]> response) {
        HttpStatusCode statusCode = response.getStatusCode();
        Country[] responseBody = response.getBody();

        if (statusCode != HttpStatus.OK || responseBody == null || responseBody.length == 0) {
            throw new CountryApiException(ExceptionMessage.FAILED_FETCH_COUNTRY);
        }
    }

}
