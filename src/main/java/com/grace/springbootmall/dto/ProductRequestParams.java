package com.grace.springbootmall.dto;

import com.grace.springbootmall.constant.ProductCategory;

public class ProductRequestParams {
    private ProductCategory category;
    private String search;

    public String getSearch() {
        return search;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
