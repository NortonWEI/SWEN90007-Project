package com.freshmel.identityMap;

import com.freshmel.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * The identity map pattern that helps to maintain data integrity.
 * this pattern aims to prevent the same data from being loaded into more than one object.
 * */
public class ProductIdentityMap {

    private static Map<Long, Product> productMap = new HashMap<Long, Product>();

    public static void putProduct(Product product) {
        productMap.put(product.getId(),product);
    }

    public static Product getProduct(Long id){
        return productMap.get(id);
    }

}
