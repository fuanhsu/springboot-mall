package com.grace.springbootmall.service.Impl;

import com.grace.springbootmall.dao.OrderDao;
import com.grace.springbootmall.dao.ProductDao;
import com.grace.springbootmall.dto.BuyItem;
import com.grace.springbootmall.dto.CreateOrderRequest;
import com.grace.springbootmall.model.Order;
import com.grace.springbootmall.model.OrderItem;
import com.grace.springbootmall.model.Product;
import com.grace.springbootmall.rowmapper.OrderRowMapper;
import com.grace.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Transient
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        Integer totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());


            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);


        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItem(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }


}
