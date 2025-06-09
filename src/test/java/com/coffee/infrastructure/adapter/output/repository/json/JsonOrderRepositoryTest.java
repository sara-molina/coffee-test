package com.coffee.infrastructure.adapter.output.repository.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.coffee.domain.model.Order;
import com.coffee.domain.model.Size;
import com.coffee.infrastructure.adapter.output.mapper.OrderMap;
import com.coffee.infrastructure.adapter.output.repository.model.OrderEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

class JsonOrderRepositoryTest {

  @Mock private OrderMap orderMap;
  @Mock private Resource ordersData;
  private JsonOrderRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    repository = new JsonOrderRepository(new ObjectMapper(), orderMap);
    ReflectionTestUtils.setField(repository, "ordersData", ordersData);
  }

  @Test
  void testFindAll() throws Exception {
    InputStream mockInputStream =
        getClass().getClassLoader().getResourceAsStream("test-orders.json");
    when(ordersData.getInputStream()).thenReturn(mockInputStream);

    OrderEntity entity =
        OrderEntity.builder().user("Alice").drink("Latte").size(Size.MEDIUM).build();
    Order expectedOrder =
        Order.builder()
            .user("Alice")
            .drink("Latte")
            .size(com.coffee.domain.model.Size.MEDIUM)
            .build();

    when(orderMap.map(entity)).thenReturn(expectedOrder);

    List<Order> orders = repository.findAll();

    assertEquals(2, orders.size());
    assertEquals("Alice", orders.get(0).getUser());
    assertEquals("Latte", orders.get(0).getDrink());
  }
}
