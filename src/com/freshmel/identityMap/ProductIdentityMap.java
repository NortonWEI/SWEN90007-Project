package com.freshmel.identityMap;

import com.freshmel.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductIdentityMap {

    private static Map<Long, Product> productMap = new HashMap<Long, Product>();

    public static void putProduct(Product product) {
        productMap.put(product.getId(),product);
    }

    public static Product getProduct(Long id){
        return productMap.get(id);
    }

}
