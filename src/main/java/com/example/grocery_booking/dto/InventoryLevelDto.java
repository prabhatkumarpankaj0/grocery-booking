package com.example.grocery_booking.dto;

import lombok.Data;

@Data
public class InventoryLevelDto {
    private Long inventoryId;
    private Long groceryItemId;
    private Integer stock;
}
