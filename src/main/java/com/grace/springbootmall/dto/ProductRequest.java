package com.grace.springbootmall.dto;

import com.grace.springbootmall.constant.ProductCategory;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ProductRequest {
    @NotNull
    private  String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    private    String imageUrl;

    @NotNull
    private    Integer price;
    @NotNull
    private    Integer stock;

    private    String description;


    public Integer getStock() {
        return stock;
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
