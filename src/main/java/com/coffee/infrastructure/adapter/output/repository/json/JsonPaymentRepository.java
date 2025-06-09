package com.coffee.infrastructure.adapter.output.repository.json;

import com.coffee.domain.model.Payment;
import com.coffee.infrastructure.adapter.output.mapper.PaymentMap;
import com.coffee.infrastructure.adapter.output.repository.PaymentRepository;
import com.coffee.infrastructure.adapter.output.repository.model.PaymentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JsonPaymentRepository extends GenericRepository<Payment, PaymentEntity>
    implements PaymentRepository {

  @Value("classpath:payments.json")
  Resource paymentsData;

  private final ObjectMapper objectMapper;

  private final PaymentMap paymentMap;

  @Override
  public List<Payment> findByUser(String user) {
    List<Payment> payments = findAll(paymentsData, objectMapper, paymentMap, PaymentEntity.class);
    return payments.stream()
        .filter(payment -> payment.getUser().equals(user))
        .collect(Collectors.toList());
  }
}
