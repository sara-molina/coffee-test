package com.coffee.infrastructure.adapter.output.mapper;

import com.coffee.domain.model.Order;
import com.coffee.infrastructure.adapter.output.repository.model.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMap extends GenericMapper<Order, OrderEntity> {}
