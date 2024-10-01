package com.grace.springbootmall.dao.Impl;

import com.grace.springbootmall.dao.OrderDao;
import com.grace.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
