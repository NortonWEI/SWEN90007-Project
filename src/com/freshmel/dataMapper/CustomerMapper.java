package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Address;
import com.freshmel.model.Customer;
import com.freshmel.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    /**
     * find customer by email only
     * @param email with email
     * @return if the customer in database return whole information of this customer
     *         if the customer not in database return null
     * */
    public Customer findByEmail(String email) throws SQLException {
        Customer result = null;
        String sql = "SELECT id,password,photo,email,phoneNumber,registerDate,lastLoginDate,firstname,lastname,line1,line2,line3,suburb,state,postCode FROM customer WHERE email=? AND password=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery() ;
        if (rs.next()) {
            result = new Customer();
            result.setId(rs.getLong(1));
            result.setPassword(rs.getString(2));
            result.setPhoto(rs.getString(3));
            result.setEmail(rs.getString(4));
            result.setPhoneNumber(rs.getString(5));
            result.setRegisterDate(rs.getTimestamp(6));
            result.setLastLoginDate(rs.getTimestamp(7));
            result.setFirstName(rs.getString(8));
            result.setLastName(rs.getString(9));
            Address address = new Address();
            address.setLine1(rs.getString(10));
            address.setLine2(rs.getString(11));
            address.setLine3(rs.getString(12));
            address.setSuburb(rs.getString(13));
            address.setState(rs.getString(14));
            address.setPostCode(rs.getString(15));
            result.setAddresses(address);
            CartMapper cartMapper = new CartMapper();
            result.setCarts(cartMapper.findByCustomerId(result.getId()));
        }
        return result;
    }

    /**
     * find customer by email and password (authentication)
     * @param customer with email and password.
     * @return if the customer in database return whole information of this customer
     *         if the customer not in database return null
     * */
    public Customer findByEmailANDPassword(Customer customer) throws SQLException {
        Customer result = null;
        String sql = "SELECT id,password,photo,email,phoneNumber,registerDate,lastLoginDate,firstname,lastname,line1,line2,line3,suburb,state,postCode FROM customer WHERE email=? AND password=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getEmail());
        pstmt.setString(2, customer.getPassword());
        ResultSet rs = pstmt.executeQuery() ;
        if (rs.next()) {
            result = new Customer();
            result.setId(rs.getLong(1));
            result.setPassword(rs.getString(2));
            result.setPhoto(rs.getString(3));
            result.setEmail(rs.getString(4));
            result.setPhoneNumber(rs.getString(5));
            result.setRegisterDate(rs.getTimestamp(6));
            result.setLastLoginDate(rs.getTimestamp(7));
            result.setFirstName(rs.getString(8));
            result.setLastName(rs.getString(9));
            Address address = new Address();
            address.setLine1(rs.getString(10));
            address.setLine2(rs.getString(11));
            address.setLine3(rs.getString(12));
            address.setSuburb(rs.getString(13));
            address.setState(rs.getString(14));
            address.setPostCode(rs.getString(15));
            result.setAddresses(address);
            CartMapper cartMapper = new CartMapper();
            result.setCarts(cartMapper.findByCustomerId(result.getId()));
        }
        return result;
    }

    /**
     * insert a customer into database
     * @param customer with the information want to insert
     * @return if insert successfully return true
     *         if insert fail return false
     * */
    public boolean insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(password,photo,email,phoneNumber,registerDate,firstname,lastname,line1,line2,line3,suburb,state,postCode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getPassword());
        pstmt.setString(2, customer.getPhoto());
        pstmt.setString(3, customer.getEmail());
        pstmt.setString(4, customer.getPhoneNumber());
        pstmt.setTimestamp(5, customer.getRegisterDate());
        pstmt.setString(6, customer.getFirstName());
        pstmt.setString(7, customer.getLastName());
        pstmt.setString(8, customer.getAddresses().getLine1());
        pstmt.setString(9, customer.getAddresses().getLine2());
        pstmt.setString(10, customer.getAddresses().getLine3());
        pstmt.setString(11, customer.getAddresses().getSuburb());
        pstmt.setString(12, customer.getAddresses().getState());
        pstmt.setString(13, customer.getAddresses().getPostCode());
        return pstmt.executeUpdate() > 0;
    }

    /**
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean resetPassword (String email) throws SQLException {
        String sql = "UPDATE customer SET password=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, User.RESET_PASSWORD);
        pstmt.setString(2, email);
        return pstmt.executeUpdate() > 0;
    }

    /**
     * update customer address
     * @param customer with the new address info
     * @return  if update successfully return true
     *          if update failed return false
     * */
    public boolean updateAddress(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET line1=?,line2=?,line3=?,suburb=?,state=?,postCode=? WHERE id=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getAddresses().getLine1());
        pstmt.setString(2, customer.getAddresses().getLine2());
        pstmt.setString(3, customer.getAddresses().getLine3());
        pstmt.setString(4, customer.getAddresses().getSuburb());
        pstmt.setString(5, customer.getAddresses().getState());
        pstmt.setString(6, customer.getAddresses().getPostCode());
        pstmt.setLong(7, customer.getId());
        return pstmt.executeUpdate() > 0;
    }


    /**
     * update photo
     * @param customer with the new photo info
     * @return if update successfully return true
     *         if update failed return flase
     * */
    public boolean updatePhoto(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET photo=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getPhoto());
        pstmt.setString(2, customer.getEmail());
        return pstmt.executeUpdate() > 0 ;
    }

    /**
     * update customer basic info
     * @param customer with the new basic customer info
     * @return if update successfully return true
     *         if update failed return false
     * */
    public boolean updateInfo(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET firstname=?,lastname=?,phoneNumber=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2, customer.getLastName());
        pstmt.setString(3, customer.getPhoneNumber());
        pstmt.setString(4, customer.getEmail());
        return pstmt.executeUpdate() > 0;
    }

}
