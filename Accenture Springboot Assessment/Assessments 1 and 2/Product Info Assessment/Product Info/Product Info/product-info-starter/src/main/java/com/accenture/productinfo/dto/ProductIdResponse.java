package com.accenture.productinfo.dto;

public class ProductIdResponse {
    private String productId;


    public ProductIdResponse(String productId) {
        this.productId = productId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
}
