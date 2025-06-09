package com.coffee.domain.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
  private String user;
  private BigDecimal amount;
}
