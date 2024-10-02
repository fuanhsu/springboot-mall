package com.grace.springbootmall.dto;

public class OrderRequestParams {
    private Integer userId;
    private Integer limit;
    private Integer offset;

    public Integer getUserId() {
        return userId;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
