package com.pinapp.ms.clientes.pinapp.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException e) {
        return getResponseEntityErrorResponseDTO(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException e) {
        return getResponseEntityErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". "));
        return getResponseEntityErrorResponseDTO(HttpStatus.BAD_REQUEST, mensaje);
    }

    private ResponseEntity<ErrorResponseDTO> getResponseEntityErrorResponseDTO(HttpStatus httpStatus, String mensaje) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                httpStatus.getReasonPhrase(),
                mensaje);
        log.error("[getResponseEntityErrorResponseDTO] {}", errorResponse);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
