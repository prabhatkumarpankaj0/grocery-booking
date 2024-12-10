package com.example.grocery_booking.repository;

import com.example.grocery_booking.model.InventoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, Long> {

    void deleteInventoryLevelByGroceryItem_Id(Long groceryItemId);
}