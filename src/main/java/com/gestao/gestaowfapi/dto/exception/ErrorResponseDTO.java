package com.gestao.gestaowfapi.dto.exception;

public record ErrorResponseDTO(String message, int httpStatusCode) {
}
