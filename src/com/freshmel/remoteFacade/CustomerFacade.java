package com.freshmel.remoteFacade;

import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.dto.CustomerAssembler;
import com.freshmel.dto.CustomerDTO;
import com.freshmel.model.Customer;

import java.sql.SQLException;

/**
 * translate coarse-grained method calls into
 * fine-grained method calls and collate the results
 */
public class CustomerFacade {
    /**
     * get a customer via email and password
     *
     * @param email the email of the customer
     * @param password the password of the customer
     * @return the customer dto identified
     * @throws SQLException
     */
    public CustomerDTO getCustomer(String email, String password) throws SQLException {
        CustomerMapper mapper = new CustomerMapper();
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);

        Customer result = mapper.findByEmailANDPassword(customer);
        CustomerDTO dto = CustomerAssembler.createCustomerDTO(result);

        return dto;
    }

    /**
     * get a customer json via email and password
     *
     * @param email the email of the customer
     * @param password the password of the customer
     * @return the customer dto found which has been serialized into a json string
     * @throws SQLException
     */
    public String getCustomerJSON(String email, String password) throws SQLException {
        CustomerMapper mapper = new CustomerMapper();
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);

        Customer result = mapper.findByEmailANDPassword(customer);
        CustomerDTO dto = CustomerAssembler.createCustomerDTO(result);

        return CustomerDTO.serialize(dto);
    }

    /**
     * create a customer via a serialized json sent from other servers
     *
     * @param json the json file to be deserialized
     * @throws SQLException
     */
    public void createCustomerJSON(String json) throws SQLException {
        CustomerDTO dto = CustomerDTO.deserialize(json);
        CustomerAssembler.createCustomer(dto);
    }

    /**
     * update a customer's photo URL via a serialized json sent from other servers
     *
     * @param json the json file to be deserialized
     * @throws SQLException
     */
    public void updateCustomerPhotoFromJSON(String json) throws SQLException {
        CustomerDTO dto = CustomerDTO.deserialize(json);
        CustomerAssembler.updateCustomerPhoto(dto);
    }

    /**
     * update a customer's personal profile via a serialized json sent from other servers
     *
     * @param json the json file to be deserialized
     * @throws SQLException
     */
    public void updateCustomerInfoFromJSON(String json) throws SQLException {
        CustomerDTO dto = CustomerDTO.deserialize(json);
        CustomerAssembler.updateCustomerInfo(dto);
    }

    /**
     * update a customer's address via a serialized json sent from other servers
     *
     * @param json the json file to be deserialized
     * @throws SQLException
     */
    public void updateCustomerAddressFromJSON(String json) throws SQLException {
        CustomerDTO dto = CustomerDTO.deserialize(json);
        CustomerAssembler.updateCustomerAddress(dto);
    }

    /**
     * update a customer's photo URL via a dto sent from other servers
     *
     * @param dto the dto to be processed by the assembler
     * @throws SQLException
     */
    public void updateCustomerPhoto(CustomerDTO dto) throws SQLException {
        CustomerAssembler.updateCustomerPhoto(dto);
    }

    /**
     * update a customer's personal profile via a dto sent from other servers
     *
     * @param dto the dto to be processed by the assembler
     * @throws SQLException
     */
    public void updateCustomerInfo(CustomerDTO dto) throws SQLException {
        CustomerAssembler.updateCustomerInfo(dto);
    }

    /**
     * update a customer's address via a dto sent from other servers
     *
     * @param dto the dto to be processed by the assembler
     * @throws SQLException
     */
    public void updateCustomerAddress(CustomerDTO dto) throws SQLException {
        CustomerAssembler.updateCustomerAddress(dto);
    }
}
