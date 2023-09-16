package com.country.management.service;

import com.country.management.domain.exception.CountryApiException;
import com.country.management.domain.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CountryApiServiceTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    private CountryApiService countryApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        countryApiService = new CountryApiService(restTemplateBuilder, "https://api.example.com/countries");
    }

    @Test
    void testGetCountries_Success() {
        Country[] mockCountries = {new Country(), new Country()};
        ResponseEntity<Country[]> mockResponseEntity = new ResponseEntity<>(mockCountries, HttpStatus.OK);

        when(restTemplate.getForEntity("https://api.example.com/countries", Country[].class))
                .thenReturn(mockResponseEntity);

        List<Country> result = countryApiService.getCountries();

        assertEquals(2, result.size());
    }

    @Test
    void testGetCountries_EmptyResponse() {
        ResponseEntity<Country[]> mockResponseEntity = new ResponseEntity<>(new Country[0], HttpStatus.OK);

        when(restTemplate.getForEntity("https://api.example.com/countries", Country[].class))
                .thenReturn(mockResponseEntity);

        assertThrows(CountryApiException.class, () -> countryApiService.getCountries());
    }

    @Test
    void testGetCountries_FailedRequest() {
        when(restTemplate.getForEntity("https://api.example.com/countries", Country[].class))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(CountryApiException.class, () -> countryApiService.getCountries());
    }

    @Test
    void testGetCountries_ApiException() {
        when(restTemplate.getForEntity("https://api.example.com/countries", Country[].class))
                .thenThrow(new RuntimeException("Simulated API error"));

        assertThrows(CountryApiException.class, () -> countryApiService.getCountries());
    }
}
