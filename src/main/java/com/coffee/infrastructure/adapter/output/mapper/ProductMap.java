package com.coffee.infrastructure.adapter.output.mapper;

import com.coffee.domain.model.Product;
import com.coffee.infrastructure.adapter.output.repository.model.ProductsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMap extends GenericMapper<Product, ProductsEntity> {}
