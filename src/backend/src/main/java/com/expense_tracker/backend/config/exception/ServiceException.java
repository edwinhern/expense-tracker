package com.expense_tracker.backend.config.exception;

public class ServiceException extends RuntimeException {
  public ServiceException(final String message) {
    super(message);
  }

  public ServiceException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
