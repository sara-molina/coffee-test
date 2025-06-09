package com.coffee.domain.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {
  private BigDecimal small;
  private BigDecimal medium;
  private BigDecimal large;
}
