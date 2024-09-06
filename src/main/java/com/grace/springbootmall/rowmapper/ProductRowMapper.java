package com.grace.springbootmall.rowmapper;

import com.grace.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setCategory(rs.getString("category"));
        product.setStock(rs.getInt("stock"));
        product.setPrice(rs.getInt("price"));
        product.setImageUrl(rs.getString("image_url"));
        product.setLastModifiedDate(rs.getDate("last_modified_date"));
        product.setCreatedDate(rs.getDate("created_date"));
        product.setDescription(rs.getString("description"));
        return product;
    }
}
