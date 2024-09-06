package com.grace.springbootmall.service;

import com.grace.springbootmall.dao.ProductDao;
import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
