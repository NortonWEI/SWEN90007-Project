package com.freshmel.model;

import com.freshmel.unitOfWork.CartUOW;

public class Cart {
    private Long customerId;
    private Product product;
    private Integer quantity;

    public Cart(String token) {}

    public Cart() {
        CartUOW.getCurrent().registerNew(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        CartUOW.getCurrent().registerDirty(this);
    }

    public void setProduct(Product product, String token) {
        this.product = product;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
        CartUOW.getCurrent().registerDirty(this);
    }

    public void setCustomerId(Long customerId, String token) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        CartUOW.getCurrent().registerDirty(this);
    }

    public void setQuantity(Integer quantity, String token) {
        this.quantity = quantity;
    }
}
