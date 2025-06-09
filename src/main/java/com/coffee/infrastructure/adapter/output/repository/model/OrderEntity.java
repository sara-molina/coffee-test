package com.coffee.infrastructure.adapter.output.repository.model;

import com.coffee.domain.model.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
  private String user;
  private String drink;
  private Size size;
}
