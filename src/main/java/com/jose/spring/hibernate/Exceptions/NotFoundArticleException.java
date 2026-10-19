package com.jose.spring.hibernate.Exceptions;

public class NotFoundArticleException extends RuntimeException{

    public NotFoundArticleException(Long id){

        super("No se ha encontrado el articulo con ID: " + id);

    }

}
