package com.expense_tracker.backend.config.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expense_tracker.backend.models.ServiceResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ServiceResponse<Object>> handleAllExceptions(final Exception e) {
    log.error("Unexpected error occurred", e);
    final ServiceResponse<Object> response = ServiceResponse.failure(
        "Error processing request: " + e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ServiceResponse<Object>> handleDataAccessException(final DataAccessException e) {
    log.error("Database error occurred", e);
    final ServiceResponse<Object> response = ServiceResponse.failure(
        "Database error: " + e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ServiceResponse<Object>> handleServiceException(final ServiceException e) {
    log.error("Service error occurred", e);
    final ServiceResponse<Object> response = ServiceResponse.failure(
        e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
