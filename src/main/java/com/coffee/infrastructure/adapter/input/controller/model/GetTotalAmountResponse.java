package com.coffee.infrastructure.adapter.input.controller.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTotalAmountResponse {
  private String name;
  private BigDecimal total;
}
