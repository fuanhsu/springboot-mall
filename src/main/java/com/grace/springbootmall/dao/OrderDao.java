package com.grace.springbootmall.dao;

import com.grace.springbootmall.dto.OrderRequestParams;
import com.grace.springbootmall.model.Order;
import com.grace.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemByOrderId(Integer orderId);
    List<Order> getOrders(OrderRequestParams orderRequestParams);
    Integer countOrder(OrderRequestParams orderRequestParams);

}
