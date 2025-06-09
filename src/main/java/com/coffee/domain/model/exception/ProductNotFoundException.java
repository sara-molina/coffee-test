package com.coffee.domain.model.exception;

public class ProductNotFoundException extends RuntimeException {
  private final String messageDescription;

  public ProductNotFoundException(final String messageDescription, final Throwable throwable) {
    this.messageDescription = messageDescription;
  }

  public ProductNotFoundException(final String messageDescription) {
    this.messageDescription = messageDescription;
  }

  public String getMessageDescription() {
    return this.messageDescription;
  }
}
