package com.example.grocery_booking.controller.admin;

import com.example.grocery_booking.dto.GroceryItemDto;
import com.example.grocery_booking.dto.request.AddGroceryItemRequest;
import com.example.grocery_booking.dto.response.BaseResponse;
import com.example.grocery_booking.dto.response.GroceryItemResponse;
import com.example.grocery_booking.services.service.GroceryItemService;
import com.example.grocery_booking.utils.MessageConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminGroceryItemController")
@RequestMapping("/admin/groceryItem")
public class GroceryItemController {

    private final GroceryItemService groceryItemService;

    @Autowired
    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<Object>> addOrUpdateGroceryItem(@RequestBody @Valid AddGroceryItemRequest request) {
        try {
            GroceryItemDto groceryItemDto = GroceryItemDto.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .build();
            groceryItemService.addOrUpdateGroceryItem(groceryItemDto);
            return ResponseEntity.ok(BaseResponse.builder().success(true).message(MessageConstant.GROCERY_ITEM_ADDED).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().success(false).message(e.getMessage()).build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Object>> deleteGroceryItem(@PathVariable Long id) {
        try {
            groceryItemService.deleteGroceryItem(id);
            return ResponseEntity.ok(
                    BaseResponse.builder().success(true).message(MessageConstant.GROCERY_ITEM_REMOVED).build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().success(false).message(e.getMessage()).build());
        }
    }

    @GetMapping("/groceries")
    public GroceryItemResponse getAllGroceryItems() {
        List<GroceryItemDto> groceryItems = groceryItemService.getAllGroceryItems();
        return GroceryItemResponse.builder().groceryItems(groceryItems).totalItems(groceryItems.size()).build();
    }
}
