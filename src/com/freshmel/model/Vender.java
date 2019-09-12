package com.freshmel.model;

import java.sql.Timestamp;
import java.util.List;

public class Vender extends User {
    private List<Product> list;

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
