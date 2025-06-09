package com.coffee.infrastructure.adapter.output.repository;

import com.coffee.domain.model.Payment;
import java.util.List;

public interface PaymentRepository {

  List<Payment> findByUser(String user);
}
