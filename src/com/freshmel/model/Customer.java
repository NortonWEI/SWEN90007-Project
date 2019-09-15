package com.freshmel.model;

import java.util.List;

/**
 * a customer extend User class
 * and have carts that contain product and quantity
 * */
public class Customer extends User {
    private Address addresses;
    private List<Cart> carts;

    public static int ADD_CART = 0;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }
}
