package com.coffee.domain.model;

import java.math.BigDecimal;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
  private String drinkName;
  private Map<Size, BigDecimal> prices;
}
