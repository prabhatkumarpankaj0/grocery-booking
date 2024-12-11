package com.example.grocery_booking.services.service;

import com.example.grocery_booking.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
}
