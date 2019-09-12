package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    public boolean insert(Cart cart) throws SQLException {
        String sql = "INSERT INTO cart(product_id,customer_id,quantity) VALUES (?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, cart.getProduct().getId());
        pstmt.setLong(2, cart.getCustomerId());
        pstmt.setInt(3, cart.getQuantity());
        return pstmt.executeUpdate() > 0;
    }

    public boolean deleteByProductId(Long productId) throws SQLException {
        String sql = "DELETE FROM cart WHERE product_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, productId);
        return pstmt.executeUpdate() > 0;
    }
}
