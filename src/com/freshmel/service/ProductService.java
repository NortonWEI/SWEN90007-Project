package com.freshmel.service;

import com.freshmel.dataMapper.ProductMapper;
import com.freshmel.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> getAllProducts() throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.getAllProduct();
    }

    public List<Product> getByType(String type) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.getByType(type);
    }
}
