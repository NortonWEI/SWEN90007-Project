package com.freshmel.model;

/**
 * Cart mapper to a middle table of many to many relationship
 * */
public class Cart {
    // many to many relationship middle table
    // foreign key from custom table
    private Long customerId;
    // foreign key from product table
    private Product product;
    // the quantity if a product in the cart
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
