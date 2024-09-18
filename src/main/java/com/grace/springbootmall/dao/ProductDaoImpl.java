package com.grace.springbootmall.dao;

import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.model.Product;
import com.grace.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date FROM product WHERE product_Id = :productId ";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> product = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return product.size() > 0 ? product.get(0) : null;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";


//      這裡先new KeyHolder 物件
        KeyHolder id = new GeneratedKeyHolder();


        Date date = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().name());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("createdDate",date);
        map.put("lastModifiedDate", date );
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);

//      此處生成新 id出來
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, id);
//      再將生成 id 的值回傳
        Integer productId= id.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET category = :category,  " +
                "product_name = :productName," +
                "price = :price,  " +
                "stock = :stock, " +
                " image_url = :imageUrl, " +
                "description = :description, " +
                "last_modified_date = :lastModifiedDate WHERE product_id = :productId";


        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("category", productRequest.getCategory().name());
        map.put("productName", productRequest.getProductName());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("lastModifiedDate" , new Date());

        namedParameterJdbcTemplate.update(sql, map);

    }
}
