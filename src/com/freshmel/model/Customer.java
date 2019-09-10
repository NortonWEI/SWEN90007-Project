package com.freshmel.model;

import java.sql.Timestamp;
import java.util.List;

public class Customer extends User {
    private Address addresses;

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }
}
