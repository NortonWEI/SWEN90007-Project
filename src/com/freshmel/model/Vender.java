package com.freshmel.model;

import com.freshmel.dataMapper.ProductMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * A vender can publish products and update products
 *
 * */
public class Vender extends User {
    // a list of products, which are published by the vender
    private List<Product> products;

    /**
     * Use Lazy loader pattern to load products
     * Lazy load - Ghost
     * */
    public List<Product> getProducts() {
        // if products is null then to query database to get data
        // if it is not null, just return the value in memory
        if (this.products == null){
            load();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    void load() {
        ProductMapper productMapper = new ProductMapper();
        if (this.products == null){
            try {
                this.products = productMapper.findByVenderID(this.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
