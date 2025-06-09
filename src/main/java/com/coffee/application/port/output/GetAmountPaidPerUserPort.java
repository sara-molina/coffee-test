package com.coffee.application.port.output;

import com.coffee.application.usecases.GetAmountPaidPerUserUseCase;
import com.coffee.domain.model.Order;
import com.coffee.domain.model.Product;
import com.coffee.domain.model.Size;
import com.coffee.domain.model.User;
import com.coffee.domain.model.exception.ProductNotFoundException;
import com.coffee.infrastructure.adapter.output.repository.OrderRepository;
import com.coffee.infrastructure.adapter.output.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAmountPaidPerUserPort implements GetAmountPaidPerUserUseCase {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  @Override
  public List<User> getAmountPaidPerUser() {
    List<Order> orders = orderRepository.findAll();
    Map<String, BigDecimal> userOrdersMap =
        orders.stream()
            .collect(
                Collectors.groupingBy(
                    Order::getUser,
                    Collectors.mapping(
                        order -> getProductPrice(order.getDrink(), order.getSize()),
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    return userOrdersMap.entrySet().stream()
        .map(user -> User.builder().name(user.getKey()).total(user.getValue()).build())
        .collect(Collectors.toList());
  }

  private BigDecimal getProductPrice(String productName, Size size) {
    Optional<Product> product = productRepository.findByName(productName);
    return product
        .map(p -> p.getPrices().get(size))
        .orElseThrow(() -> new ProductNotFoundException("Product " + productName + " not found"));
  }
}
