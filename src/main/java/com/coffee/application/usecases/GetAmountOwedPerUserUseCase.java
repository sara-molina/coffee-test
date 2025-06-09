package com.coffee.application.usecases;

import com.coffee.domain.model.User;
import java.util.List;

public interface GetAmountOwedPerUserUseCase {

  List<User> getAmountOwedPerUser();
}
