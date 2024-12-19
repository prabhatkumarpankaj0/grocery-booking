package com.example.grocery_booking.dto.request;

import com.example.grocery_booking.dto.InventoryLevelDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateInventoryLevelRequest {
    List<InventoryLevelDto> inventoryLevels;
}
