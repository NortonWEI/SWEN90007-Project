package com.freshmel.dto;

import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.model.Address;
import com.freshmel.model.Customer;

import java.sql.SQLException;

/**
 * a customer assembler processes DTO encapsulation and domain object update
 */
public class CustomerAssembler {
    /**
     * create a new customer DTO from a customer domain object
     *
     * @param customer the customer domain object to be encapsulated
     * @return a coarse-grained DTO to be serialized
     */
    public static CustomerDTO createCustomerDTO(Customer customer) {
        CustomerDTO result = new CustomerDTO();
        Address address = customer.getAddresses();

        result.setId(customer.getId());
        result.setEmail(customer.getEmail());
        result.setPassword(customer.getPassword());
        result.setFirstName(customer.getFirstName());
        result.setLastName(customer.getLastName());
        result.setPhoto(customer.getPhoto());
        result.setPhoneNumber(customer.getPhoneNumber());
        result.setRegisterDate(customer.getRegisterDate());
        result.setLine1(address.getLine1());
        result.setLine2(address.getLine2());
        result.setLine3(address.getLine3());
        result.setSuburb(address.getSuburb());
        result.setState(address.getState());
        result.setPostCode(address.getPostCode());
        result.setCarts(customer.getCarts());

        return result;
    }

    /**
     * create a customer from customer DTO and insert into the database with the data mapper
     *
     * @param customerDto the customer DTO to be handled
     * @throws SQLException
     */
    public static void createCustomer(CustomerDTO customerDto) throws SQLException {
        Customer customer = new Customer();
        Address address = new Address();

        customer.setId(customerDto.getId());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoto(customerDto.getPhoto());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setRegisterDate(customerDto.getRegisterDate());
        address.setLine1(customerDto.getLine1());
        address.setLine2(customerDto.getLine2());
        address.setLine3(customerDto.getLine3());
        address.setSuburb(customerDto.getSuburb());
        address.setState(customerDto.getState());
        address.setPostCode(customerDto.getPostCode());

        customer.setAddresses(address);

        CustomerMapper mapper = new CustomerMapper();
        mapper.insert(customer);
    }

    /**
     * update a customer photo URL from a customer DTO
     *
     * @param customerDto the customer DTO whose photo URL is to be updated
     * @throws SQLException
     */
    public static void updateCustomerPhoto(CustomerDTO customerDto) throws SQLException {
        Customer customer = new Customer();
        customer.setPhoto(customerDto.getPhoto());
        customer.setEmail(customerDto.getEmail());

        CustomerMapper mapper = new CustomerMapper();
        mapper.updatePhoto(customer);
    }

    /**
     * update a customer info from a customer DTO
     *
     * @param customerDto the customer DTO whose info is to be updated
     * @throws SQLException
     */
    public static void updateCustomerInfo(CustomerDTO customerDto) throws SQLException {
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        CustomerMapper mapper = new CustomerMapper();
        mapper.updateInfo(customer);
    }

    /**
     * update a customer address from a customer DTO
     *
     * @param customerDto the customer DTO whose address is to be updated
     * @throws SQLException
     */
    public static void updateCustomerAddress(CustomerDTO customerDto) throws SQLException {
        Customer customer = new Customer();
        Address address = new Address();

        address.setLine1(customerDto.getLine1());
        address.setLine2(customerDto.getLine2());
        address.setLine3(customerDto.getLine3());
        address.setSuburb(customerDto.getSuburb());
        address.setState(customerDto.getState());
        address.setPostCode(customerDto.getPostCode());

        customer.setAddresses(address);

        CustomerMapper mapper = new CustomerMapper();
        mapper.updateAddress(customer);
    }
}
