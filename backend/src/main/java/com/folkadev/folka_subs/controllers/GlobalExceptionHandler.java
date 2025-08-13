package com.folkadev.folka_subs.controllers;

import com.folkadev.folka_subs.domain.dto.ErrorResponse;
import com.folkadev.folka_subs.exceptions.ResourceAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {

  @ExceptionHandler({ IllegalArgumentException.class })
  public ResponseEntity<ErrorResponse> handleExceptions(RuntimeException ex, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ ResourceAlreadyExistsException.class })
  public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(ResourceAlreadyExistsException ex,
      WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.CONFLICT.value(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }
}
