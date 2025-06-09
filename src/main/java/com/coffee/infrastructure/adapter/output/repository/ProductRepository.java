package com.coffee.infrastructure.adapter.output.repository;

import com.coffee.domain.model.Product;
import java.util.Optional;

public interface ProductRepository {
  Optional<Product> findByName(String productName);
}
