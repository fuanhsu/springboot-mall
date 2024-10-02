package com.grace.springbootmall.dao.Impl;

import com.grace.springbootmall.dao.OrderDao;
import com.grace.springbootmall.dto.OrderRequestParams;
import com.grace.springbootmall.model.Order;
import com.grace.springbootmall.model.OrderItem;
import com.grace.springbootmall.rowmapper.OrderItemRowMapper;
import com.grace.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date)  " +
                "VALUES(:userId, :totalAmount, :createDate, :lastModifiedDate) ";
        Date now = new Date();

        Map<String, Object> map = new HashMap();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);
        map.put("createDate", now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount)  " +
                "VALUES (:orderId, :productId, :quantity, :amount) ";
        MapSqlParameterSource[] mapSqlParameterSources = new MapSqlParameterSource[orderItemList.size()];
        for(int i=0;i<orderItemList.size();i++){
            mapSqlParameterSources[i] = new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("orderId", orderId);
            mapSqlParameterSources[i].addValue("productId", orderItemList.get(i).getProductId());
            mapSqlParameterSources[i].addValue("quantity",orderItemList.get(i).getQuantity());
            mapSqlParameterSources[i].addValue("amount", orderItemList.get(i).getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSources);

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date FROM `order` WHERE order_id = :orderId ";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orderList.size() > 0 ? orderList.get(0): null;

    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id,  oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url " +
                "FROM order_item oi LEFT JOIN product p " +
                "ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId ";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        return namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
    }



    @Override
    public List<Order> getOrders(OrderRequestParams orderRequestParams) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date FROM `order` " +
                "WHERE 1 = 1 ";
        Map<String, Object> map = new HashMap<>();
        sql = addFilterSql(sql, map, orderRequestParams);

        sql += "ORDER BY created_date DESC ";

        sql += "LIMIT :limit OFFSET :offset ";

        map.put("limit", orderRequestParams.getLimit());
        map.put("offset", orderRequestParams.getOffset());


        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map,  new OrderRowMapper());


        return orderList;
    }

    private String addFilterSql(String sql, Map<String, Object> map, OrderRequestParams orderRequestParams) {
        if (orderRequestParams.getUserId() != null) {
            sql += "AND user_id = :userId ";
            map.put("userId", orderRequestParams.getUserId());
        }
        return sql;
    }

    @Override
    public Integer countOrder(OrderRequestParams orderRequestParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1 = 1 ";
        Map<String, Object> map = new HashMap<>();
        sql = addFilterSql(sql, map, orderRequestParams);

        return namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
    }

}
