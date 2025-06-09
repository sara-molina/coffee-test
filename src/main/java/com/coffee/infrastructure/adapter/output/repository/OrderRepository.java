package com.coffee.infrastructure.adapter.output.repository;

import com.coffee.domain.model.Order;
import java.util.List;

public interface OrderRepository {
  List<Order> findAll();
}
