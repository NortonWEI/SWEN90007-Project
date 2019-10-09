package com.freshmel.dto;

import com.freshmel.model.Cart;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.List;

/**
 * a customer data transfer object class
 * use this class to serialize and deserialize
 * and communicate with other servers over a network
 */
public class CustomerDTO {
    // user info
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String photo;
    private String phoneNumber;
    private Timestamp registerDate;

    // address info
    private String line1;
    private String line2;
    private String line3;
    private String suburb;
    private String state;
    private String postCode;

    // carts info
    private List<Cart> carts;

    /**
     * serialize a Customer Data Transfer Object (DTO) to be sent over a network.
     * we use a google json parser "gson" here.
     *
     * @param customerDto the Customer DTO to be serialized
     * @return a json string containing the Customer DTO
     */
    public static String serialize(CustomerDTO customerDto) {
        Gson gson = new Gson();
        return gson.toJson(customerDto);
    }

    /**
     * deserialize a json string to a Customer DTO for further use in the system.
     *
     * @param customerDtoStr the json string to be serialized
     * @return a Customer DTO extracted from the json string
     */
    public static CustomerDTO deserialize(String customerDtoStr) {
        Gson gson = new Gson();
        return gson.fromJson(customerDtoStr, CustomerDTO.class);
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getState() {
        return state;
    }

    public String getPostCode() {
        return postCode;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
