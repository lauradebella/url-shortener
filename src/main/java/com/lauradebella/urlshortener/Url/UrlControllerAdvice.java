package com.lauradebella.urlshortener.Url;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UrlControllerAdvice {

    @ExceptionHandler(value = {UrlNotFoundException.class})
    public ResponseEntity handleDeclinedTransaction(UrlNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}