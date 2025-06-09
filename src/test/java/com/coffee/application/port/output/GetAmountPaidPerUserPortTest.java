package com.coffee.application.port.output;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coffee.domain.model.*;
import com.coffee.domain.model.exception.ProductNotFoundException;
import com.coffee.infrastructure.adapter.output.repository.OrderRepository;
import com.coffee.infrastructure.adapter.output.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GetAmountPaidPerUserPortTest {

  @Mock private OrderRepository orderRepository;

  @Mock private ProductRepository productRepository;

  @InjectMocks private GetAmountPaidPerUserPort getAmountPaidPerUserPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAmountPaidPerUser() {
    Order order1 = Order.builder().user("Bob").drink("Latte").size(Size.MEDIUM).build();

    Order order2 = Order.builder().user("Bob").drink("Latte").size(Size.SMALL).build();

    List<Order> orders = List.of(order1, order2);

    Map<Size, BigDecimal> lattePrices = new HashMap<>();
    lattePrices.put(Size.SMALL, new BigDecimal("2.00"));
    lattePrices.put(Size.MEDIUM, new BigDecimal("3.00"));

    Product latte = Product.builder().drinkName("Latte").prices(lattePrices).build();

    when(orderRepository.findAll()).thenReturn(orders);
    when(productRepository.findByName("Latte")).thenReturn(Optional.of(latte));

    List<User> result = getAmountPaidPerUserPort.getAmountPaidPerUser();

    assertEquals(1, result.size());
    User user = result.get(0);
    assertEquals("Bob", user.getName());
    assertEquals(new BigDecimal("5.00"), user.getTotal());
  }

  @Test
  void testProductNotFoundThrowsException() {
    Order order = Order.builder().user("Alice").drink("Mocha").size(Size.LARGE).build();

    when(orderRepository.findAll()).thenReturn(List.of(order));
    when(productRepository.findByName("Mocha")).thenReturn(Optional.empty());

    assertThrows(
        ProductNotFoundException.class, () -> getAmountPaidPerUserPort.getAmountPaidPerUser());
  }
}
