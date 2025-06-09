package com.coffee.infrastructure.adapter.output.repository.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.coffee.domain.model.Payment;
import com.coffee.infrastructure.adapter.output.mapper.PaymentMap;
import com.coffee.infrastructure.adapter.output.repository.model.PaymentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

class JsonPaymentRepositoryTest {

  @Mock private PaymentMap paymentMap;
  @Mock private Resource paymentsData;

  private JsonPaymentRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    repository = new JsonPaymentRepository(new ObjectMapper(), paymentMap);
    ReflectionTestUtils.setField(repository, "paymentsData", paymentsData);
  }

  @Test
  void testFindByUser() throws Exception {
    InputStream mockInputStream =
        getClass().getClassLoader().getResourceAsStream("test-payments.json");
    when(paymentsData.getInputStream()).thenReturn(mockInputStream);

    PaymentEntity entity1 =
        PaymentEntity.builder().user("Alice").amount(new BigDecimal("3.50")).build();
    PaymentEntity entity2 =
        PaymentEntity.builder().user("Bob").amount(new BigDecimal("2.00")).build();

    Payment payment1 = Payment.builder().user("Alice").amount(new BigDecimal("3.50")).build();
    Payment payment2 = Payment.builder().user("Bob").amount(new BigDecimal("2.00")).build();

    when(paymentMap.map(entity1)).thenReturn(payment1);
    when(paymentMap.map(entity2)).thenReturn(payment2);

    List<Payment> result = repository.findByUser("Alice");

    assertEquals(1, result.size());
    assertEquals("Alice", result.get(0).getUser());
    assertEquals(new BigDecimal("3.50"), result.get(0).getAmount());
  }
}
