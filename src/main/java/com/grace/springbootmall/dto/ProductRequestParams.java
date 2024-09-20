package com.grace.springbootmall.dto;

import com.grace.springbootmall.constant.ProductCategory;

public class ProductRequestParams {
    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;

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

    public String getOrderBy() {
        return orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
