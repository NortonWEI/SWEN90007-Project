package com.freshmel.model;

import com.freshmel.dataMapper.ProductMapper;

import java.sql.SQLException;
import java.util.List;

public class Vender extends User {
    private List<Product> products;

    public List<Product> getProducts() throws SQLException {
        if (this.products == null){
            ProductMapper productMapper = new ProductMapper();
            this.products = productMapper.findByVenderID(this.getId());
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
