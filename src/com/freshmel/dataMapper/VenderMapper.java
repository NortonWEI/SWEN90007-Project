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

    public Vender findByEmail(String email) throws SQLException {
        Vender vender = null;
        String sql = "SELECT id,password,photo,email,phoneNumber,registerDate,lastLoginDate,firstname,lastname FROM vender WHERE email=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery() ;
        if (rs.next()) {
            vender = new Vender();
            vender.setId(rs.getLong(1));
            vender.setPassword(rs.getString(2));
            vender.setPhoto(rs.getString(3));
            vender.setEmail(rs.getString(4));
            vender.setPhoneNumber(rs.getString(5));
            vender.setRegisterDate(rs.getTimestamp(6));
            vender.setLastLoginDate(rs.getTimestamp(7));
            vender.setFirstName(rs.getString(8));
            vender.setLasteName(rs.getString(9));
        }
        return vender;
    }
}
