package com.grace.springbootmall.service;

import com.grace.springbootmall.dto.CreateOrderRequest;
import com.grace.springbootmall.dto.OrderRequestParams;
import com.grace.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    Integer countOrder(OrderRequestParams orderRequestParams);

    List<Order> getOrders(OrderRequestParams orderRequestParams);
}
