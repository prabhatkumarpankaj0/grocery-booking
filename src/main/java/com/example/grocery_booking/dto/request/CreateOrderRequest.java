package com.example.grocery_booking.dto.request;

import com.example.grocery_booking.dto.OrderDetailDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderRequest {
    @NotNull(message = "user id cannot be null")
    private Long customerId;
    @NotEmpty(message = "order details cannot be empty")
    private List<OrderDetailDto> orderDetails;
    private Double totalPrice;
}
