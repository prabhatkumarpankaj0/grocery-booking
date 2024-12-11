package com.example.grocery_booking.services.service;

import com.example.grocery_booking.dto.GroceryItemDto;

import java.util.List;

public interface GroceryItemService {

    void addOrUpdateGroceryItem(GroceryItemDto groceryItemDto);
    void deleteGroceryItem(Long groceryItemId);
    List<GroceryItemDto> getAllGroceryItems();
}
