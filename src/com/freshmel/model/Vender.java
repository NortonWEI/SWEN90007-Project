package com.freshmel.model;

import java.sql.Timestamp;
import java.util.List;

public class Vender extends User {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
