package com.example.grocery_booking.services.service;

import com.example.grocery_booking.dto.InventoryLevelDto;

import java.util.List;

public interface InventoryLevelService {

    void updateInventoryLevels(List<InventoryLevelDto> inventoryLevelDtoList);
}
