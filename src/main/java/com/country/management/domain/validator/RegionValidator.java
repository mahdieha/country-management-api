package com.country.management.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;


public class RegionValidator implements ConstraintValidator<ValidRegion, String> {

    private static final HashSet<String> ALLOWED_REGIONS = new HashSet<>(List.of("asia", "europe", "africa", "americas", "oceania"));

    @Override
    public boolean isValid(String region, ConstraintValidatorContext context) {
        return ALLOWED_REGIONS.contains(region.toLowerCase());
    }
}
