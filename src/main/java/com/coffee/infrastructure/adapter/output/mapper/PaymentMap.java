package com.coffee.infrastructure.adapter.output.mapper;

import com.coffee.domain.model.Payment;
import com.coffee.infrastructure.adapter.output.repository.model.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMap extends GenericMapper<Payment, PaymentEntity> {}
