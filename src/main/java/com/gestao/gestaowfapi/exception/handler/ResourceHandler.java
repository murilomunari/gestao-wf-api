package com.gestao.gestaowfapi.exception.handler;

import com.gestao.gestaowfapi.dto.exception.ErrorResponseDTO;
import com.gestao.gestaowfapi.exception.BadRequestException;
import com.gestao.gestaowfapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Mono<ErrorResponseDTO>> notFoundException(NotFoundException ex){

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(errorResponseDTO));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Mono<ErrorResponseDTO>> badRequestException(BadRequestException ex){

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(errorResponseDTO));
    }
}
