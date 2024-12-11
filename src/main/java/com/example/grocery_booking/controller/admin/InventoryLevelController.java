package com.example.grocery_booking.controller.admin;

import com.example.grocery_booking.dto.request.UpdateInventoryLevelRequest;
import com.example.grocery_booking.dto.response.BaseResponse;
import com.example.grocery_booking.services.service.InventoryLevelService;
import com.example.grocery_booking.utils.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/inventory")
public class InventoryLevelController {

    @Autowired
    private InventoryLevelService inventoryLevelService;

    @PostMapping("")
    public ResponseEntity<BaseResponse<Object>> updateInventoryLevels(@RequestBody UpdateInventoryLevelRequest request) {
        try {
            inventoryLevelService.updateInventoryLevels(request.getInventoryLevels());
            return ResponseEntity.ok(
                    BaseResponse.builder().success(true).message(MessageConstant.INVENTORY_LEVEL_UPDATED).build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().success(false).message(e.getMessage()).build());
        }
    }
}
