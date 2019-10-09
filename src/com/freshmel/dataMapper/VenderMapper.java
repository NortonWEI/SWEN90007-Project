package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Product;
import com.freshmel.model.Vender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VenderMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    /**
     * find vender by email and password
     * @param vender with email an d password.
     * @return if vender in database return the vender
     *         if vender not in database return null
     * */
    public Vender findByEmailANDPassword(Vender vender) throws SQLException {
        Vender result = null;
        String sql = "SELECT id,password,photo,email,phoneNumber,registerDate,lastLoginDate,firstname,lastname FROM vender WHERE email=? AND password=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, vender.getEmail());
        pstmt.setString(2, vender.getPassword());
        ResultSet rs = pstmt.executeQuery() ;
        if (rs.next()) {
            result = new Vender();
            result.setId(rs.getLong(1));
            result.setPassword(rs.getString(2));
            result.setPhoto(rs.getString(3));
            result.setEmail(rs.getString(4));
            result.setPhoneNumber(rs.getString(5));
            result.setRegisterDate(rs.getTimestamp(6));
            result.setLastLoginDate(rs.getTimestamp(7));
            result.setFirstName(rs.getString(8));
            result.setLastName(rs.getString(9));

            ProductMapper productMapper = new ProductMapper();
            List<Product> products = productMapper.findByVenderID(result.getId());
            result.setProducts(products);
        }
        return result;
    }

    /**
     * insert vender to database
     * @param vender with vender information
     * @return if insert successfully return true
     *         if insert failed return false
     * */
    public boolean insert(Vender vender) throws SQLException {
        String sql = "INSERT INTO vender(password,photo,email,phoneNumber,registerDate,firstname,lastname) VALUES (?,?,?,?,?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, vender.getPassword());
        pstmt.setString(2, vender.getPhoto());
        pstmt.setString(3, vender.getEmail());
        pstmt.setString(4, vender.getPhoneNumber());
        pstmt.setTimestamp(5, vender.getRegisterDate());
        pstmt.setString(6, vender.getFirstName());
        pstmt.setString(7, vender.getLastName());
        return pstmt.executeUpdate() > 0;
    }

    /**
     * update vender photo
     * @param vender with the new photo info
     * @return if update successfully return true
     *         if update failed return false
     * */
    public boolean updatePhoto(Vender vender) throws SQLException {
        String sql = "UPDATE vender SET photo=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, vender.getPhoto());
        pstmt.setString(2, vender.getEmail());
        return pstmt.executeUpdate() > 0 ;
    }

    /**
     * update vender basic info
     * @param vender with the new basic info
     * @return if update successfully return true
     *         if update failed return flase
     * */
    public boolean updateInfo(Vender vender) throws SQLException {
        String sql = "UPDATE vender SET firstname=?,lastname=?,phoneNumber=? WHERE email=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, vender.getFirstName());
        pstmt.setString(2, vender.getFirstName());
        pstmt.setString(3, vender.getPhoneNumber());
        pstmt.setString(4, vender.getEmail());
        return pstmt.executeUpdate() > 0;
    }

}
