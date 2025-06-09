package com.coffee.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Size {
  SMALL,
  MEDIUM,
  LARGE,
  HUGE,
  MEGA,
  ULTRA;

  @JsonCreator
  public static Size from(String key) {
    return Size.valueOf(key.toUpperCase());
  }
}
