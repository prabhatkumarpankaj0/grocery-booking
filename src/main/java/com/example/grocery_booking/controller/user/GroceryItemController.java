package com.example.grocery_booking.controller.user;

import com.example.grocery_booking.dto.GroceryItemDto;
import com.example.grocery_booking.dto.response.GroceryItemResponse;
import com.example.grocery_booking.services.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userGroceryItemController")
@RequestMapping("/user/groceryItem")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping("/groceries")
    public GroceryItemResponse getAllGroceryItems() {
        List<GroceryItemDto> groceryItems = groceryItemService.getAllGroceryItems();
        return GroceryItemResponse.builder().groceryItems(groceryItems).totalItems(groceryItems.size()).build();
    }
}
