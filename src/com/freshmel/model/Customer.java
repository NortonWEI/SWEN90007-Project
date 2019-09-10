package com.freshmel.model;

import java.sql.Timestamp;
import java.util.List;

public class Customer extends User {
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
