package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Customer;
import com.freshmel.model.Vender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    public boolean insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(password,photo,email,phoneNumber,registerDate,firstname,lastname) VALUES (?,?,?,?,?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getPassword());
        pstmt.setString(2, customer.getPhoto());
        pstmt.setString(3, customer.getEmail());
        pstmt.setString(4, customer.getPhoneNumber());
        pstmt.setTimestamp(5, customer.getRegisterDate());
        pstmt.setString(6, customer.getFirstName());
        pstmt.setString(7, customer.getLasteName());
        if (pstmt.executeUpdate() > 0){
            customer = findByEmailANDPassword(customer);
            pstmt = conn.prepareStatement("INSERT INTO address(customer_id) VALUES (?)") ;
            pstmt.setLong(1, customer.getId());
            return pstmt.executeUpdate() > 0;
        }
        return false;
    }

    public Customer findByEmailANDPassword(Customer customer) throws SQLException {
        Customer result = null;
        String sql = "SELECT id,password,photo,email,phoneNumber,registerDate,lastLoginDate,firstname,lastname FROM customer WHERE email=? AND password=?";
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
            result.setLasteName(rs.getString(9));
            CartMapper cartMapper = new CartMapper();
            result.setCarts(cartMapper.findByCustomerId(result.getId()));
        }
        return result;
    }

    public boolean updatePhoto(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET photo=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getPhoto());
        pstmt.setString(2, customer.getEmail());
        return pstmt.executeUpdate() > 0 ;
    }

    public boolean updateInfo(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET firstname=?,lastname=?,phoneNumber=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2, customer.getLasteName());
        pstmt.setString(3, customer.getPhoneNumber());
        pstmt.setString(4, customer.getEmail());
        return pstmt.executeUpdate() > 0;
    }

}
