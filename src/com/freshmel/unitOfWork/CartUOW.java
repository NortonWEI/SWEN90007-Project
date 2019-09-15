package com.freshmel.unitOfWork;

import com.freshmel.dataMapper.CartMapper;
import com.freshmel.model.Cart;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartUOW {
    private static ThreadLocal current = new ThreadLocal();

    private List<Cart> newCarts = new ArrayList<Cart>();
    private List<Cart> dirtyCarts = new ArrayList<Cart>();
    private List<Cart> deletedCarts = new ArrayList<Cart>();

    public static void newCurrent() {
        setCurrent(new CartUOW());
    }

    public static void setCurrent(CartUOW cartUOW) {
        current.set(cartUOW);
    }

    public static CartUOW getCurrent() {
        return (CartUOW) current.get();
    }

    public void registerNew(Cart cart) {
//        Assert.assertNotNull ("id is null", cart.getCustomerId());
        Assert.assertFalse("object is dirty", dirtyCarts.contains(cart));
        Assert.assertFalse("object is deleted", deletedCarts.contains(cart));
        Assert.assertFalse("object is new", newCarts.contains(cart));
        newCarts.add(cart);
    }

    public void registerDirty(Cart cart) {
//        Assert.assertNotNull("id is null", cart.getCustomerId());

        Assert.assertFalse("object is deleted", deletedCarts.contains(cart));
        if (!dirtyCarts.contains(cart) && !newCarts.contains(cart)) {
            dirtyCarts.add(cart);
        }
    }

    public void registerDeleted(Cart cart) {
//        Assert.assertNotNull("id is null", cart.getCustomerId());
        if (newCarts.remove(cart)) return;
        dirtyCarts.remove(cart);
        if (!deletedCarts.contains(cart)) {
            deletedCarts.add(cart);
        }
    }

    public void registerClean(Cart cart) {
        Assert.assertNotNull("id is null", cart.getCustomerId());
    }

    public boolean commit() throws SQLException {
        boolean success = true;
        for (Cart cart : newCarts) {
            CartMapper cartMapper = new CartMapper();
            success = cartMapper.safeInsert(cart);
            if (!success) return success;
        }
        for (Cart cart : dirtyCarts) {
            CartMapper cartMapper = new CartMapper();
            success = cartMapper.safeInsert(cart);
            if (!success) return success;
        }
        for (Cart cart : deletedCarts) {
            CartMapper cartMapper = new CartMapper();
            success = cartMapper.deleteByProductIdAndCustomerId(cart.getProduct().getId(), cart.getCustomerId());
            if (!success) return success;
        }
        return success;
    }
}
