package com.coffee.infrastructure.adapter.output.repository.json;

import com.coffee.domain.model.Product;
import com.coffee.infrastructure.adapter.output.mapper.ProductMap;
import com.coffee.infrastructure.adapter.output.repository.ProductRepository;
import com.coffee.infrastructure.adapter.output.repository.model.ProductsEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JsonProductRepository extends GenericRepository<Product, ProductsEntity>
    implements ProductRepository {

  @Value("classpath:products.json")
  Resource productsData;

  private final ObjectMapper objectMapper;
  private final ProductMap productMap;

  @Override
  public Optional<Product> findByName(String productName) {
    return findAll(productsData, objectMapper, productMap, ProductsEntity.class).stream()
        .filter(product -> productName.equals(product.getDrinkName()))
        .findFirst();
  }
}
