package com.grace.springbootmall.service.Impl;

import com.grace.springbootmall.dao.ProductDao;
import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.dto.ProductRequestParams;
import com.grace.springbootmall.model.Product;
import com.grace.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

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

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
           productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public List<Product> getProducts(ProductRequestParams productRequestParams) {
       return productDao.getProducts(productRequestParams);
    }

    @Override
    public Integer countProduct(ProductRequestParams productRequestParams) {
        return productDao.countProduct(productRequestParams);
    }
}
