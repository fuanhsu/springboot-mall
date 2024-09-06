package com.grace.springbootmall.service;

import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
