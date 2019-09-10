package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Address;
import com.freshmel.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    public Address findByCustomerId(Customer customer) throws SQLException {
        Address result = null;
        String sql = "SELECT firstName,lastName,phone,line1,line2,line3,suburb,state,postCode,id,customer_id FROM address WHERE customer_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, customer.getId());
        ResultSet rs = pstmt.executeQuery() ;
        if (rs.next()) {
            result = new Address();
            result.setFirstName(rs.getString(1));
            result.setLastName(rs.getString(2));
            result.setPhone(rs.getString(3));
            result.setLine1(rs.getString(4));
            result.setLine2(rs.getString(5));
            result.setLine3(rs.getString(6));
            result.setSuburb(rs.getString(7));
            result.setState(rs.getString(8));
            result.setPostCode(rs.getString(9));
            result.setId(rs.getLong(10));
            result.setCustomerId(rs.getLong(11));
        }
        return result;
    }

    public boolean update(Address address) throws SQLException {
        String sql = "UPDATE address SET firstName=?,lastName=?,phone=?,line1=?,line2=?,line3=?,suburb=?,state=?,postCode=? WHERE customer_id=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, address.getFirstName());
        pstmt.setString(2, address.getLastName());
        pstmt.setString(3, address.getPhone());
        pstmt.setString(4, address.getLine1());
        pstmt.setString(5, address.getLine2());
        pstmt.setString(6, address.getLine3());
        pstmt.setString(7, address.getSuburb());
        pstmt.setString(8, address.getState());
        pstmt.setString(9, address.getPostCode());
        pstmt.setLong(10, address.getCustomerId());
        return pstmt.executeUpdate() > 0;
    }
}
