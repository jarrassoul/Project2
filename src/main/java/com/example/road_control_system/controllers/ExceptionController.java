package com.example.road_control_system.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.road_control_system.exceptions.ApiError;
import com.example.road_control_system.exceptions.CustomNotFoundException;

@RestController
@ControllerAdvice
public class ExceptionController {
            private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = CustomNotFoundException.class)
    public ApiError handleNotFoundException(CustomNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e.getPath());
        return apiError;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("No handler found for URL: {}", ex.getRequestURL());
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "The requested resource was not found",
                ex.getRequestURL()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
