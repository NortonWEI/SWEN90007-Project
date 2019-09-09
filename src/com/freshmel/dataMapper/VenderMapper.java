package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Vender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VenderMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

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
            result.setLasteName(rs.getString(9));
        }
        return result;
    }

    public boolean insert(Vender vender) throws SQLException {
        String sql = "INSERT INTO vender(password,photo,email,phoneNumber,lastLoginDate,firstname,lastname) VALUES (?,?,?,?,?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, vender.getPassword());
        pstmt.setString(2, vender.getPhoto());
        pstmt.setString(3, vender.getEmail());
        pstmt.setString(4, vender.getPhoneNumber());
        pstmt.setTimestamp(5, vender.getRegisterDate());
        pstmt.setString(6, vender.getFirstName());
        pstmt.setString(7, vender.getLasteName());
        return pstmt.executeUpdate() > 0;
    }

    public boolean update(Vender vender){
        return false;
    }

}
