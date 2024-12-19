package com.example.grocery_booking.controller.user;

import com.example.grocery_booking.dto.OrderDto;
import com.example.grocery_booking.dto.request.CreateOrderRequest;
import com.example.grocery_booking.dto.response.BaseResponse;
import com.example.grocery_booking.dto.response.CreateOrderResponse;
import com.example.grocery_booking.services.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        try {
            OrderDto orderDto = orderService.createOrder(
                    OrderDto.builder()
                            .userId(request.getCustomerId())
                            .orderDetailDtos(request.getOrderDetails())
                            .totalPrice(request.getTotalPrice())
                            .build()
            );
            return ResponseEntity.ok(
                    CreateOrderResponse.builder()
                            .id(orderDto.getId())
                            .customerId(orderDto.getUserId())
                            .orderDetails(orderDto.getOrderDetailDtos())
                            .totalPrice(orderDto.getTotalPrice())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().success(false).message(e.getMessage()).build());
        }
    }
}
