package com.coffee.infrastructure.adapter.output.repository.json;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coffee.domain.model.Product;
import com.coffee.infrastructure.adapter.output.mapper.ProductMap;
import com.coffee.infrastructure.adapter.output.repository.model.ProductsEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

class JsonProductRepositoryTest {

  @Mock private ProductMap productMap;
  @Mock private Resource productsData;

  private JsonProductRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    repository = new JsonProductRepository(new ObjectMapper(), productMap);
    ReflectionTestUtils.setField(repository, "productsData", productsData);
  }

  @Test
  void testFindByName() throws Exception {
    InputStream mockInputStream =
        getClass().getClassLoader().getResourceAsStream("test-products.json");
    when(productsData.getInputStream()).thenReturn(mockInputStream);

    ProductsEntity entity1 =
        ProductsEntity.builder()
            .drinkName("Latte")
            .prices(
                Map.of(
                    com.coffee.domain.model.Size.SMALL, new BigDecimal("2.50"),
                    com.coffee.domain.model.Size.MEDIUM, new BigDecimal("3.00"),
                    com.coffee.domain.model.Size.LARGE, new BigDecimal("3.50")))
            .build();

    Product product =
        Product.builder()
            .drinkName("Latte")
            .prices(
                Map.of(
                    com.coffee.domain.model.Size.SMALL, new BigDecimal("2.50"),
                    com.coffee.domain.model.Size.MEDIUM, new BigDecimal("3.00"),
                    com.coffee.domain.model.Size.LARGE, new BigDecimal("3.50")))
            .build();

    when(productMap.map(entity1)).thenReturn(product);

    Optional<Product> result = repository.findByName("Latte");

    assertTrue(result.isPresent());
    assertEquals("Latte", result.get().getDrinkName());
    assertEquals(
        new BigDecimal("3.00"), result.get().getPrices().get(com.coffee.domain.model.Size.MEDIUM));
  }
}
