package com.coffee.infrastructure.adapter.input.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.coffee.application.usecases.GetAmountOwedPerUserUseCase;
import com.coffee.application.usecases.GetAmountPaidPerUserUseCase;
import com.coffee.domain.model.User;
import com.coffee.infrastructure.adapter.input.controller.mapper.UserMapper;
import com.coffee.infrastructure.adapter.input.controller.model.GetTotalAmountResponse;
import com.coffee.infrastructure.adapter.input.controller.model.GetTotalOwedResponse;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeControllerTest {

  private GetAmountPaidPerUserUseCase getAmountPaidPerUserUseCase;
  private GetAmountOwedPerUserUseCase getAmountOwedPerUserUseCase;
  private UserMapper mapper;
  private CoffeeController controller;

  @BeforeEach
  void setUp() {
    getAmountPaidPerUserUseCase = mock(GetAmountPaidPerUserUseCase.class);
    getAmountOwedPerUserUseCase = mock(GetAmountOwedPerUserUseCase.class);
    mapper = mock(UserMapper.class);

    controller =
        new CoffeeController(getAmountPaidPerUserUseCase, getAmountOwedPerUserUseCase, mapper);
  }

  @Test
  void testGetAmountPaidPerUser() {

    User user = User.builder().name("Alice").total(new BigDecimal("10.00")).build();
    GetTotalAmountResponse response =
        GetTotalAmountResponse.builder().name("Alice").total(new BigDecimal("10.00")).build();

    when(getAmountPaidPerUserUseCase.getAmountPaidPerUser()).thenReturn(List.of(user));
    when(mapper.map(user)).thenReturn(response);

    var result = controller.getAmountPaidPerUser();

    assertEquals(1, result.getBody().size());
    assertEquals("Alice", result.getBody().get(0).getName());
    assertEquals(new BigDecimal("10.00"), result.getBody().get(0).getTotal());
  }

  @Test
  void testGetAmountOwedPerUserUseCase() {

    User user = User.builder().name("Bob").totalOwed(new BigDecimal("5.00")).build();
    GetTotalOwedResponse response =
        GetTotalOwedResponse.builder().name("Bob").totalOwed(new BigDecimal("5.00")).build();

    when(getAmountOwedPerUserUseCase.getAmountOwedPerUser()).thenReturn(List.of(user));
    when(mapper.mapTotalOwed(user)).thenReturn(response);

    var result = controller.getAmountOwedPerUserUseCase();

    assertEquals(1, result.getBody().size());
    assertEquals("Bob", result.getBody().get(0).getName());
    assertEquals(new BigDecimal("5.00"), result.getBody().get(0).getTotalOwed());
  }
}
