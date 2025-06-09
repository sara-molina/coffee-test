package com.coffee.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
  private String name;
  private BigDecimal totalOwed;
  private BigDecimal total;
}
