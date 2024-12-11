package com.example.grocery_booking.services.implementation;

import com.example.grocery_booking.dto.GroceryItemDto;
import com.example.grocery_booking.exception.ResourceNotFoundException;
import com.example.grocery_booking.model.GroceryItem;
import com.example.grocery_booking.model.InventoryLevel;
import com.example.grocery_booking.repository.GroceryItemRepository;
import com.example.grocery_booking.repository.InventoryLevelRepository;
import com.example.grocery_booking.services.service.GroceryItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;
    @Autowired
    private InventoryLevelRepository inventoryLevelRepository;

    @Override
    public void addOrUpdateGroceryItem(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem;

        if (Objects.nonNull(groceryItemDto.getId())) {
            groceryItem = groceryItemRepository.findById(groceryItemDto.getId()).orElse(null);
        } else {
            groceryItem = GroceryItem.builder()
                    .name(groceryItemDto.getName())
                    .description(groceryItemDto.getDescription())
                    .price(groceryItemDto.getPrice()).build();
        }

        if (Objects.isNull(groceryItem)) {
            throw new ResourceNotFoundException("Grocery Item Not Found");
        }
        if (groceryItemDto.getId() == null) {
            inventoryLevelRepository.save(
                    InventoryLevel.builder()
                            .groceryItem(groceryItem)
                            .stock(1)
                            .build()
            );
        }
        groceryItemRepository.save(groceryItem);
    }

    @Override
    @Transactional
    public void deleteGroceryItem(Long groceryItemId) {
        groceryItemRepository.deleteById(groceryItemId);
        inventoryLevelRepository.deleteInventoryLevelByGroceryItem_Id(groceryItemId);
    }

    @Override
    public List<GroceryItemDto> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        List<InventoryLevel> inventoryLevels = inventoryLevelRepository.findAll();
        Map<Long, InventoryLevel> groceryIdInventoryMap = inventoryLevels.stream()
                .collect(Collectors.toMap(inventoryLevel -> inventoryLevel.getGroceryItem().getId(),
                        inventoryLevel -> inventoryLevel));

        return groceryItems.stream().map(
                groceryItem -> GroceryItemDto.builder()
                        .id(groceryItem.getId())
                        .name(groceryItem.getName())
                        .description(groceryItem.getDescription())
                        .price(groceryItem.getPrice())
                        .inventoryQuantity(groceryIdInventoryMap.get(groceryItem.getId()).getStock())
                        .build())
                .collect(Collectors.toList());
    }


}
