package com.country.management.domain.exception;


import com.country.management.domain.dtos.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(CountryApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handleCustomException(CountryApiException ex) {
        ErrorDTO error = new ErrorDTO();

        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.addError(ex.getMessage());

        // Optionally log the exception
        logger.error(ex.getMessage(), ex);

        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        ErrorDTO error = new ErrorDTO();
        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError("Invalid region. Region must be one of Asia, Europe, Africa, Americas or Oceania.");

        // Optionally log the exception
        logger.error(ex.getMessage(), ex);

        return ResponseEntity.badRequest().body(error);
    }

}