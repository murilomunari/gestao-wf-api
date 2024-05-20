package com.gestao.gestaowfapi.exception;

public class BadRequestException extends RuntimeException{

    BadRequestException(final String message){
        super(message);
    }
}
