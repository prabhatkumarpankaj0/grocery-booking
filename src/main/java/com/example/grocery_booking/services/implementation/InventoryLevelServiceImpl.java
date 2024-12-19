package com.example.grocery_booking.services.implementation;

import com.example.grocery_booking.dto.InventoryLevelDto;
import com.example.grocery_booking.model.InventoryLevel;
import com.example.grocery_booking.repository.InventoryLevelRepository;
import com.example.grocery_booking.services.service.InventoryLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryLevelRepository;

    @Autowired
    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryLevelRepository) {
        this.inventoryLevelRepository = inventoryLevelRepository;
    }

    @Override
    public void updateInventoryLevels(List<InventoryLevelDto> inventoryLevelDtoList) {
        List<Long> inventoryLevelIds = inventoryLevelDtoList.stream()
                .map(InventoryLevelDto::getInventoryId)
                .toList();

        List<InventoryLevel> inventoryLevels = inventoryLevelRepository.findAllById(inventoryLevelIds);
        Map<Long, InventoryLevelDto> inventoryLevelDtoMap = inventoryLevelDtoList.stream().collect(Collectors.toMap(InventoryLevelDto::getInventoryId, dto -> dto));

        inventoryLevels.forEach(inventoryLevel ->
                inventoryLevel.setStock(inventoryLevelDtoMap.get(inventoryLevel.getId()).getStock())
        );
        inventoryLevelRepository.saveAll(inventoryLevels);
    }
}
