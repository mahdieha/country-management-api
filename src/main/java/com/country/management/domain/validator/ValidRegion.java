package com.country.management.domain.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegionValidator.class)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegion {

    String message() default "Invalid region";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}