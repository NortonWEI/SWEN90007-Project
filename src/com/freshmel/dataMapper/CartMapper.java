package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Cart;
import com.freshmel.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    /**
     * insert cart into database
     * @param cart
     * @return if insert successfully return true
     *         if failed return false
     * */
    public boolean insert(Cart cart) throws SQLException {
        String sql = "INSERT INTO cart(product_id,customer_id,quantity) VALUES (?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, cart.getProduct().getId());
        pstmt.setLong(2, cart.getCustomerId());
        pstmt.setInt(3, cart.getQuantity());
        return pstmt.executeUpdate() > 0;
    }

    /**
     * delete by productId and CustomerID
     * @param productId
     * @param customerId
     * @return if delete successfully return true
     *         if failed return false
     * */
    public boolean deleteByProductIdAndCustomerId(Long productId, Long customerId) throws SQLException {
        String sql = "DELETE FROM cart WHERE product_id=? AND customer_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, productId);
        pstmt.setLong(2, customerId);
        return pstmt.executeUpdate() > 0;
    }


    /**
     * delete by CustomerID
     * @param customerId
     * @return if delete successfully return true
     *         if failed return false
     * */
    public boolean deleteByCustomerId(Long customerId) throws SQLException {
        String sql = "DELETE FROM cart WHERE customer_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, customerId);
        return pstmt.executeUpdate() > 0;
    }

    /**
     * find carts by customerId
     * @param customerID
     * @return a list of carts belongs the customer
     * */
    public List<Cart> findByCustomerId(Long customerID) throws SQLException {
        String sql = "SELECT product_id,customer_id,quantity FROM cart WHERE customer_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, customerID);
        ResultSet rs = pstmt.executeQuery();
        List<Cart> carts = new ArrayList<Cart>();
        while (rs.next()){
            Cart cart = new Cart("findByCustId");
            cart.setProduct(new Product(), "findByCustId");
            cart.getProduct().setId(rs.getLong(1));
            cart.setCustomerId(rs.getLong(2), "findByCustId");
            cart.setQuantity(rs.getInt(3), "findByCustId");
            carts.add(cart);
        }
        ProductMapper productMapper = new ProductMapper();
        for (int i=0;i<carts.size();i++){
            Product product = productMapper.findByProductID(carts.get(i).getProduct().getId());
            carts.get(i).setProduct(product, "findByCustId");
        }
        return carts;
    }

//    public boolean updateQuantity(Cart cart) throws SQLException {
//        String sql = "UPDATE cart SET quantity=quantity+? WHERE product_id=? and customer_id=?" ;
//        pstmt = conn.prepareStatement(sql) ;
//        pstmt.setInt(1, cart.getQuantity());
//        pstmt.setLong(2, cart.getProduct().getId());
//        pstmt.setLong(3, cart.getCustomerId());
//        return pstmt.executeUpdate() > 0;
//    }

    /**
     * safe insert: if productId and customerId in database just add quantity to that row
     * @param cart
     * @return if insert successfully return true
     *         if failed return false
     * */
    public boolean safeInsert(Cart cart) throws SQLException {
        String sql = "insert into cart (product_id,customer_id,quantity)  values(?,?,?) on  DUPLICATE key update quantity=quantity+values(quantity)" ;
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, cart.getProduct().getId());
        pstmt.setLong(2, cart.getCustomerId());
        pstmt.setInt(3, cart.getQuantity());
        return pstmt.executeUpdate() > 0;
    }
}
