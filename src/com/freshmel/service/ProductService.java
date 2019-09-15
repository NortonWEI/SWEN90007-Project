package com.freshmel.service;

import com.freshmel.dataMapper.ProductMapper;
import com.freshmel.identityMap.ProductIdentityMap;
import com.freshmel.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    public Product getByProductId(Long id) throws SQLException {
        // identity map
        // before query database, it look up at ProductIdentityMap first
        Product product = ProductIdentityMap.getProduct(id);
        // if it is not in ProductIdentityMap, then query database and put the result into ProductIdentityMap
        if (product == null){
            ProductMapper productMapper = new ProductMapper();
            product = productMapper.findByProductID(id);
            ProductIdentityMap.putProduct(product);
        }
        return product;
    }

    public List<Product> getAllProducts() throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        List<Product> products = productMapper.getAllProduct();
        for (int i =0;i<products.size();i++){
            ProductIdentityMap.putProduct(products.get(i));
        }
        return products;
    }

    public List<Product> getByType(String type) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        List<Product> products = productMapper.getByType(type);
        for (int i =0;i<products.size();i++){
            ProductIdentityMap.putProduct(products.get(i));
        }
        return products;
    }
}
