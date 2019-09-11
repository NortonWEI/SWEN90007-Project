package com.freshmel.service;

import com.freshmel.dataMapper.AddressMapper;
import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.model.Address;
import com.freshmel.model.Customer;

import java.sql.SQLException;

public class CustomerService {
    public boolean register(Customer customer) throws SQLException {
        customer.setPhoto("default.png");
        customer.setPhoneNumber(" ");
        customer.setFirstName(" ");
        customer.setLasteName(" ");
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.insert(customer);
    }
    public Customer login(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        AddressMapper addressMapper = new AddressMapper();
        customer = customerMapper.findByEmailANDPassword(customer);
        Address address = addressMapper.findByCustomerId(customer);
        customer.setAddresses(address);
        return customer;
    }

    public boolean updatePhoto(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.updatePhoto(customer);
    }

    public boolean updateInfo(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.updateInfo(customer);
    }

    public boolean updateAddress(Address address) throws SQLException {
        AddressMapper addressMapper = new AddressMapper();
        return  addressMapper.update(address);
    }
}
