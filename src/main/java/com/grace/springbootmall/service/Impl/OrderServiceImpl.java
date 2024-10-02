package com.grace.springbootmall.service.Impl;

import com.grace.springbootmall.dao.OrderDao;
import com.grace.springbootmall.dao.ProductDao;
import com.grace.springbootmall.dao.UserDao;
import com.grace.springbootmall.dto.BuyItem;
import com.grace.springbootmall.dto.CreateOrderRequest;
import com.grace.springbootmall.dto.OrderRequestParams;
import com.grace.springbootmall.model.Order;
import com.grace.springbootmall.model.OrderItem;
import com.grace.springbootmall.model.Product;
import com.grace.springbootmall.model.User;
import com.grace.springbootmall.rowmapper.OrderRowMapper;
import com.grace.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    @Transient
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("該 userId {} 不存在 ", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        Integer totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());


            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;

            if (product == null){
                log.warn("該商品 {} 不存在", buyItem.getProductId());
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("該商品 prodcutId {} 庫存數量 {} 不足，購買數量 {} " , product.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(buyItem.getProductId(), product.getStock() - buyItem.getQuantity());

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

    @Override
    public Integer countOrder(OrderRequestParams orderRequestParams) {
        return orderDao.countOrder(orderRequestParams);
    }

    @Override
    public List<Order> getOrders(OrderRequestParams orderRequestParams) {
        List<Order> orderList = orderDao.getOrders(orderRequestParams);

        for(Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }


}
