package com.coffee.application.port.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.coffee.application.usecases.GetAmountPaidPerUserUseCase;
import com.coffee.domain.model.Payment;
import com.coffee.domain.model.User;
import com.coffee.domain.model.exception.ProductNotFoundException;
import com.coffee.infrastructure.adapter.output.repository.PaymentRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GetAmountOwedPerUserPortTest {

  @Mock private GetAmountPaidPerUserUseCase getAmountPaidPerUserUseCase;

  @Mock private PaymentRepository paymentRepository;

  @InjectMocks private GetAmountOwedPerUserPort getAmountOwedPerUserPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAmountOwedPerUser_OK() {
    User user = User.builder().name("Alice").total(new BigDecimal("50.00")).build();

    List<User> users = List.of(user);

    Payment payment1 = Payment.builder().user("Alice").amount(new BigDecimal("10.00")).build();
    Payment payment2 = Payment.builder().user("Alice").amount(new BigDecimal("15.00")).build();
    List<Payment> payments = List.of(payment1, payment2);

    when(getAmountPaidPerUserUseCase.getAmountPaidPerUser()).thenReturn(users);
    when(paymentRepository.findByUser("Alice")).thenReturn(payments);

    List<User> result = getAmountOwedPerUserPort.getAmountOwedPerUser();

    assertEquals(1, result.size());
    User resultUser = result.get(0);
    assertEquals("Alice", resultUser.getName());
    assertEquals(new BigDecimal("25.00"), resultUser.getTotalOwed());
  }

  @Test
  void testGetAmountOwedPerUser_KO() {

    when(getAmountPaidPerUserUseCase.getAmountPaidPerUser())
        .thenThrow(new ProductNotFoundException("Product invented coffee not found"));

    ProductNotFoundException ex =
        Assertions.assertThrows(
            ProductNotFoundException.class, () -> getAmountOwedPerUserPort.getAmountOwedPerUser());

    assertEquals(ex.getMessageDescription(), "Product invented coffee not found");
  }
}
