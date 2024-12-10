package com.example.grocery_booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddGroceryItemRequest {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @NotNull
    @Positive(message = "Price must be positive")
    private Double price;
}
