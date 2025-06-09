package com.coffee.infrastructure.adapter.output.repository.model;

import com.coffee.domain.model.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
  @JsonProperty("drink_name")
  private String drinkName;

  private Map<Size, BigDecimal> prices;
}
