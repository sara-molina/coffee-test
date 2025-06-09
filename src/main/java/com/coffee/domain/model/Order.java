package com.coffee.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private String user;
  private String drink;
  private Size size;
}
