package com.coffee.infrastructure.adapter.input.controller.mapper;

import com.coffee.domain.model.User;
import com.coffee.infrastructure.adapter.input.controller.model.GetTotalAmountResponse;
import com.coffee.infrastructure.adapter.input.controller.model.GetTotalOwedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  GetTotalAmountResponse map(User user);

  GetTotalOwedResponse mapTotalOwed(User user);
}
