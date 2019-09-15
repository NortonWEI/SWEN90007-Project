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

    /**
     * add new cart to newCarts
     * @param cart the newly created cart to be added to newCartList
     */
    public void registerNew(Cart cart) {
        Assert.assertFalse("object is dirty", dirtyCarts.contains(cart));
        Assert.assertFalse("object is deleted", deletedCarts.contains(cart));
        Assert.assertFalse("object is new", newCarts.contains(cart));
        newCarts.add(cart);
    }

    /**
     * add dirty cart to dirtyCarts
     * @param cart the cart whose attributes has been changed should be added to dirtyCartList
     */
    public void registerDirty(Cart cart) {

        Assert.assertFalse("object is deleted", deletedCarts.contains(cart));
        if (!dirtyCarts.contains(cart) && !newCarts.contains(cart)) {
            dirtyCarts.add(cart);
        }
    }

    /**
     * add delete cart to deletedCartList
     * @param cart the cart deleted by the user is going to be added into deletedCartList
     */
    public void registerDeleted(Cart cart) {
        if (newCarts.remove(cart)) return;
        dirtyCarts.remove(cart);
        if (!deletedCarts.contains(cart)) {
            deletedCarts.add(cart);
        }
    }

    /**
     *
     * @param cart
     */
    public void registerClean(Cart cart) {

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
