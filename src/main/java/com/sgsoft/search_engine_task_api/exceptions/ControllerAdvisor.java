package com.sgsoft.search_engine_task_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e){
        CustomException customException = new CustomException(e.getMessage(),HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<Object> handleTagNotFoundException(TagNotFoundException e){
        CustomException customException = new CustomException(e.getMessage(),HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }
}
