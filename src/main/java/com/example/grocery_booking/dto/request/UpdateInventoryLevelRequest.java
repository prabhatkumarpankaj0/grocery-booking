package com.example.grocery_booking.dto.request;

import com.example.grocery_booking.dto.InventoryLevelDto;
import lombok.Data;

import java.util.List;

@Data
public class UpdateInventoryLevelRequest {
    List<InventoryLevelDto> inventoryLevels;
}
