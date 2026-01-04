package com.example.EcommerceSpring.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //add all the error that may come over here itself;
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    //handleCategoryNotFound----
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound (CategoryNotFoundException ex){
        ErrorResponse err = new ErrorResponse(
          HttpStatus.NOT_FOUND.value(),
          ex.getMessage(),
          LocalDateTime.now()
        );
        return new ResponseEntity<>(err , HttpStatus.NOT_FOUND);
    }

    //Generic Exception -----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(){
        ErrorResponse err = new ErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR.value(),
          "An unexpected error occured. We are working on it. :)",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(err , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
