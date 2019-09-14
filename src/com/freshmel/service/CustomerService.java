package com.freshmel.service;

import com.freshmel.dataMapper.CartMapper;
import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.model.Address;
import com.freshmel.model.Cart;
import com.freshmel.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    public boolean register(Customer customer) throws SQLException {
        customer.setPhoto("default.png");
        customer.setPhoneNumber(" ");
        customer.setFirstName(" ");
        customer.setLasteName(" ");
        Address address = new Address();
        address.setLine1(" ");
        address.setLine2(" ");
        address.setLine3(" ");
        address.setPostCode(" ");
        address.setState(" ");
        address.setSuburb(" ");
        customer.setAddresses(address);
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.insert(customer);
    }
    public Customer login(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        customer = customerMapper.findByEmailANDPassword(customer);
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

    public boolean updateAddress(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return  customerMapper.updateAddress(customer);
    }

    public boolean addToCart (Cart cart) throws SQLException {
        CartMapper cartMapper = new CartMapper();
        return cartMapper.safeInsert(cart);
    }

    public List<Cart> getCarts(Long customerId) throws SQLException {
        CartMapper cartMapper = new CartMapper();
        return cartMapper.findByCustomerId(customerId);
    }

    public boolean deleteCartItem(Long productId, Long customerId) throws SQLException {
        CartMapper cartMapper = new CartMapper();
        return cartMapper.deleteByProductIdAndCustomerId(productId,customerId);
    }
}
