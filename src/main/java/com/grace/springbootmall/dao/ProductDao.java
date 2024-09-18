package com.grace.springbootmall.dao;

import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
