package com.example.grocery_booking.services.implementation;

import com.example.grocery_booking.dto.OrderDetailDto;
import com.example.grocery_booking.dto.OrderDto;
import com.example.grocery_booking.exception.CustomValidationException;
import com.example.grocery_booking.exception.ResourceNotFoundException;
import com.example.grocery_booking.model.*;
import com.example.grocery_booking.repository.*;
import com.example.grocery_booking.services.service.OrderService;
import com.example.grocery_booking.utils.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private InventoryLevelRepository inventoryLevelRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public OrderDto createOrder(OrderDto requestOrderDto) {

        User user = userRepository.findById(requestOrderDto.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found")
                );
        double totalPrice = 0.0;
        List<OrderDetailDto> responseOrderDetailDtos = new ArrayList<>();

        for (OrderDetailDto orderDetailDto : requestOrderDto.getOrderDetailDtos()) {
            InventoryLevel inventoryLevel = inventoryLevelRepository.findByGroceryItem_Id(orderDetailDto.getGroceryItemId())
                    .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.GROCERY_ITEM_NOT_FOUND_IN_INVENTORY));

            if (inventoryLevel.getStock() < orderDetailDto.getQuantity()) {
                throw new CustomValidationException("Insufficient stock for item " + inventoryLevel.getGroceryItem().getName());
            }
            inventoryLevel.setStock(inventoryLevel.getStock() - orderDetailDto.getQuantity());
            inventoryLevelRepository.save(inventoryLevel);

            double itemTotalPrice = inventoryLevel.getGroceryItem().getPrice() * orderDetailDto.getQuantity();
            totalPrice += itemTotalPrice;

            responseOrderDetailDtos.add(
                    OrderDetailDto.builder()
                            .groceryItemId(orderDetailDto.getGroceryItemId())
                            .quantity(orderDetailDto.getQuantity())
                            .totalItemPrice(itemTotalPrice).build()
            );

        }

        //save Order
        Order order = Order.builder().user(user).totalPrice(totalPrice).build();
        orderRepository.save(order);
        //save  OrderDetail
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDto orderDetailDto : responseOrderDetailDtos) {
            GroceryItem groceryItem = groceryItemRepository.findById(orderDetailDto.getGroceryItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Grocery item not found"));

            orderDetails.add(
                    OrderDetail.builder()
                            .order(order)
                            .groceryItem(groceryItem)
                            .quantity(orderDetailDto.getQuantity())
                            .totalItemPrice(orderDetailDto.getTotalItemPrice())
                            .build()
            );
        }
        orderDetailRepository.saveAll(orderDetails);


        return OrderDto.builder()
                .userId(requestOrderDto.getUserId())
                .orderDetailDtos(responseOrderDetailDtos)
                .totalPrice(totalPrice)
                .build();
    }
}
