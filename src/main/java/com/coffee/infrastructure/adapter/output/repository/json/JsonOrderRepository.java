package com.coffee.infrastructure.adapter.output.repository.json;

import com.coffee.domain.model.Order;
import com.coffee.infrastructure.adapter.output.mapper.OrderMap;
import com.coffee.infrastructure.adapter.output.repository.OrderRepository;
import com.coffee.infrastructure.adapter.output.repository.model.OrderEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JsonOrderRepository extends GenericRepository<Order, OrderEntity>
    implements OrderRepository {

  @Value("classpath:orders.json")
  Resource ordersData;

  private final ObjectMapper objectMapper;
  private final OrderMap orderMap;

  @Override
  public List<Order> findAll() {
    return findAll(ordersData, objectMapper, orderMap, OrderEntity.class);
  }
}
