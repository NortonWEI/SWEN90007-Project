package com.freshmel.unitOfWork;

import com.freshmel.model.Product;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class CartUOW {
    private static ThreadLocal current = new ThreadLocal();

    private List<Product> newProducts = new ArrayList<Product>();
    private List<Product> dirtyProducts = new ArrayList<Product>();
    private List<Product> deletedProducts = new ArrayList<Product>();

    public static void newCurrent() {
        setCurrent(new CartUOW());
    }

    public static void setCurrent(CartUOW cartUOW) {
        current.set(cartUOW);
    }

    public static CartUOW getCurrent() {
        return (CartUOW) current.get();
    }

    public void registerNew(Product product) {
        Assert.assertNotNull ("id is null", product.getId());
        Assert.assertFalse("object is dirty", dirtyProducts.contains(product));
        Assert.assertFalse("object is deleted", deletedProducts.contains(product));
        Assert.assertFalse("object is new", newProducts.contains(product));
        newProducts.add(product);
    }

    public void registerDirty(Product product) {
        Assert.assertNotNull("id is null", product.getId());

        Assert.assertFalse("object is deleted", deletedProducts.contains(product));
        if (!dirtyProducts.contains(product) && !newProducts.contains(product)) {
            dirtyProducts.add(product);
        }
    }

    public void registerDeleted(Product product) {
        Assert.assertNotNull("id is null", product.getId());
        if (newProducts.remove(product)) return;
        dirtyProducts.remove(product);
        if (!deletedProducts.contains(product)) {
            deletedProducts.add(product);
        }
    }

    public void registerClean(Product product) {
        Assert.assertNotNull("id is null", product.getId());
    }

    public void commit() {
        for (Product product : newProducts) {
//            DataMapper.getMapper(obj.getClass()).insert(obj);
        }
        for (Product product : dirtyProducts) {
//            DataMapper.getMapper(obj.getClass()).update(obj);
        }
        for (Product product : deletedProducts) {
//            DataMapper.getMapper(obj.getClass()).delete(obj);
        }
    }
}
