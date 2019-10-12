package com.freshmel.service;

import com.freshmel.dataMapper.CartMapper;
import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.model.Address;
import com.freshmel.model.Cart;
import com.freshmel.model.Customer;
import com.freshmel.unitOfWork.CartUOW;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    /**
     * customer register
     * @param customer with email and password
     * @return if register successfully return true
     *         if register failed return false
     * */
    public boolean register(Customer customer) throws SQLException {
        customer.setPhoto("default.png");
        customer.setPhoneNumber(" ");
        customer.setFirstName(" ");
        customer.setLastName(" ");
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

    /**
     * customer login
     * @param customer with email and password
     * @return if login successfully return the customer with all info stored in database
     *         if login failed return null
     * */
    public Customer login(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        customer = customerMapper.findByEmailANDPassword(customer);
        return customer;
    }

    /**
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean resetPassword(String email, String oldPassword, String newPassword) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.resetPassword(email, oldPassword, newPassword);
    }

    /**
     * update customer photo
     * @param customer with the new photo info
     * @return if update successfully return true
     *         if failed return false
     * */
    public boolean updatePhoto(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.updatePhoto(customer);
    }

    /**
     * update customer basic info
     * @param customer with the new basic info
     * @return if update successfully return true
     *         if failed return false
     * */
    public boolean updateInfo(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return customerMapper.updateInfo(customer);
    }

    /**
     * update customer address
     * @param customer with the new address info
     * @return if update successfully return true
     *         if failed return false
     * */
    public boolean updateAddress(Customer customer) throws SQLException {
        CustomerMapper customerMapper = new CustomerMapper();
        return  customerMapper.updateAddress(customer);
    }

    /**
     * commit the UOW lists (new, dirty, deleted) result into the database
     * @return if commit successfully then return true
     *          if failed return false
     * @throws SQLException
     */
    public boolean addToCart() throws SQLException {
        return CartUOW.getCurrent().commit();
    }

    /**
     * get carts by customerId
     * @param customerId
     * @return a list of carts belongs to the customer
     * */
    public List<Cart> getCarts(Long customerId) throws SQLException {
        CartMapper cartMapper = new CartMapper();
        return cartMapper.findByCustomerId(customerId);
    }

    /**
     * delete cart item
     * @param productId
     * @param customerId
     * @return if delete successfully return true
     *         if failed return false
     * */
    public boolean deleteCartItem(Long productId, Long customerId) throws SQLException {
        CartMapper cartMapper = new CartMapper();
        return cartMapper.deleteByProductIdAndCustomerId(productId,customerId);
    }
}
