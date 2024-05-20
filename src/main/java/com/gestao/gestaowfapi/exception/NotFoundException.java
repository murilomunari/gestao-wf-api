package com.gestao.gestaowfapi.exception;

public class NotFoundException extends RuntimeException{

    NotFoundException(final String message){
        super(message);
    }
}

