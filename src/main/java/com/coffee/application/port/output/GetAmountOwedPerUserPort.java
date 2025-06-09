package com.coffee.application.port.output;

import com.coffee.application.usecases.GetAmountOwedPerUserUseCase;
import com.coffee.application.usecases.GetAmountPaidPerUserUseCase;
import com.coffee.domain.model.*;
import com.coffee.infrastructure.adapter.output.repository.PaymentRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAmountOwedPerUserPort implements GetAmountOwedPerUserUseCase {

  private final GetAmountPaidPerUserUseCase getAmountPaidPerUserUseCase;
  private final PaymentRepository paymentRepository;

  @Override
  public List<User> getAmountOwedPerUser() {
    List<User> users = getAmountPaidPerUserUseCase.getAmountPaidPerUser();
    return users.stream()
        .map(user -> user.toBuilder().totalOwed(getDebt(user.getName(), user.getTotal())).build())
        .collect(Collectors.toList());
  }

  private BigDecimal getDebt(String userName, BigDecimal debt) {
    return debt.subtract(getTotalPaidByUser(userName));
  }

  private BigDecimal getTotalPaidByUser(String userName) {
    List<Payment> payments = paymentRepository.findByUser(userName);
    return payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
