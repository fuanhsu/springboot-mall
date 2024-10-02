package com.grace.springbootmall.service;

import com.grace.springbootmall.dto.CreateOrderRequest;
import com.grace.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
