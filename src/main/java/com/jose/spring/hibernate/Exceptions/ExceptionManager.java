package com.jose.spring.hibernate.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class ExceptionManager {

    @ExceptionHandler(NotFoundArticleException.class)
    public ResponseEntity<String> articuloNoEncontrado(NotFoundArticleException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ex.getMessage());

    }

}
