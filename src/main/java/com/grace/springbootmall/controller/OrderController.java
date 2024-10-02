package com.grace.springbootmall.controller;

import com.grace.springbootmall.dto.BuyItem;
import com.grace.springbootmall.dto.CreateOrderRequest;
import com.grace.springbootmall.dto.OrderRequestParams;
import com.grace.springbootmall.model.Order;
import com.grace.springbootmall.service.OrderService;
import com.grace.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId, @RequestBody @Valid CreateOrderRequest createOrderRequest) {


        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
                                                 @RequestParam(defaultValue =  "0") @Min(0) Integer offset){
        OrderRequestParams orderRequestParams = new OrderRequestParams();
        orderRequestParams.setUserId(userId);
        orderRequestParams.setLimit(limit);
        orderRequestParams.setOffset(offset);

        List<Order> orderList = orderService.getOrders(orderRequestParams);

        Integer count = orderService.countOrder(orderRequestParams);
        Page<Order> page = new Page<Order>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setResults(orderList);
        page.setTotal(count);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


}
