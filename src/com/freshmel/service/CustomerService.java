package com.freshmel.service;

import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.model.Customer;

import java.sql.SQLException;

public class CustomerService {
    public boolean register(Customer customer) throws SQLException {
        // 1 check is the email in database
        // 2 if not in database then insert to database
        // 2 else return false
        customer.setPhoto("default.png");
        customer.setPhoneNumber(" ");
        customer.setFirstName(" ");
        customer.setLasteName(" ");
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.insert(customer);
    }
    public Customer login(Customer customer) throws SQLException {
        // 1 check is the email and password in database correct
        // 2 if correct return true
        // 2 else return false
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.findByEmailANDPassword(customer);
    }

    public boolean updatePhoto(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.updatePhoto(customer);
    }
}
