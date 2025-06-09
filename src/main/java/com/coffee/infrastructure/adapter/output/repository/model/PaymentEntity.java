package com.coffee.infrastructure.adapter.output.repository.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
  private String user;
  private BigDecimal amount;
}
