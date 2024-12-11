package com.example.grocery_booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDetailDto {
    @NotNull(message = "Grocery item ID cannot be null")
    private Long groceryItemId;
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be atleast 1")
    private Integer quantity;
    private Double totalItemPrice;
}
