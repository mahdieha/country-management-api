package com.country.management.domain.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class RegionValidator implements ConstraintValidator<ValidRegion, String> {

    private static final String[] ALLOWED_REGIONS = {
            "Asia", "Europe", "Africa", "Americas", "Oceania"
    };

    @Override
    public boolean isValid(String region, ConstraintValidatorContext context) {
        for (String allowedRegion : ALLOWED_REGIONS) {
            if (allowedRegion.equalsIgnoreCase(region)) {
                return true;
            }
        }
        return false;
    }
}
