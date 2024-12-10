package com.example.grocery_booking.services.service;

import com.example.grocery_booking.dto.GroceryItemDto;

public interface GroceryItemService {

    public void addOrUpdateGroceryItem(GroceryItemDto groceryItemDto);
    public void deleteGroceryItem(Long groceryItemId);
}
