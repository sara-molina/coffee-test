package com.coffee.application.usecases;

import com.coffee.domain.model.User;
import java.util.List;

public interface GetAmountPaidPerUserUseCase {
  List<User> getAmountPaidPerUser();
}
