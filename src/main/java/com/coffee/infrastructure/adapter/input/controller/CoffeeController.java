package com.coffee.infrastructure.adapter.input.controller;

import com.coffee.application.usecases.GetAmountOwedPerUserUseCase;
import com.coffee.application.usecases.GetAmountPaidPerUserUseCase;
import com.coffee.domain.model.User;
import com.coffee.infrastructure.adapter.input.controller.mapper.UserMapper;
import com.coffee.infrastructure.adapter.input.controller.model.GetTotalAmountResponse;
import com.coffee.infrastructure.adapter.input.controller.model.GetTotalOwedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/coffee")
public class CoffeeController {

  private final GetAmountPaidPerUserUseCase getAmountPaidPerUserUseCase;
  private final GetAmountOwedPerUserUseCase getAmountOwedPerUserUseCase;
  private final UserMapper mapper;

  @GetMapping("/amount")
  @Operation(summary = "Get total amount of users orders")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "404", description = "The product is not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  public ResponseEntity<List<GetTotalAmountResponse>> getAmountPaidPerUser() {
    List<User> user = getAmountPaidPerUserUseCase.getAmountPaidPerUser();
    return ResponseEntity.ok(user.stream().map(mapper::map).collect(Collectors.toList()));
  }

  @GetMapping("/debt")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "404", description = "The product is not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  public ResponseEntity<List<GetTotalOwedResponse>> getAmountOwedPerUserUseCase() {
    List<User> user = getAmountOwedPerUserUseCase.getAmountOwedPerUser();
    return ResponseEntity.ok(user.stream().map(mapper::mapTotalOwed).collect(Collectors.toList()));
  }
}
