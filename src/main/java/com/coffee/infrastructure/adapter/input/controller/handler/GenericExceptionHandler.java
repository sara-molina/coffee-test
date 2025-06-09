package com.coffee.infrastructure.adapter.input.controller.handler;

import com.coffee.domain.model.exception.ErrorModelApi;
import com.coffee.domain.model.exception.ProductNotFoundException;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorModelApi> handleNotFoundException(ProductNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorModelApi.builder().code("404").description(ex.getMessageDescription()).build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorModelApi> handleNotFoundException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ErrorModelApi.builder()
                .code("500")
                .description(
                    Objects.nonNull(ex.getMessage()) ? ex.getMessage() : "Unexpected server error")
                .build());
  }
}
