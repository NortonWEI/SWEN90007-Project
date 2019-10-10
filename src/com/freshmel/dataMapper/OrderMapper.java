package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Order;
import com.freshmel.model.OrderItem;
import com.freshmel.model.Product;

import java.sql.*;

public class OrderMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    /**
     * insert a order into database
     * @param order with the information want to insert
     * @return if insert successfully return true
     *         if insert fail return false
     * */
    public boolean insert(Order order) throws SQLException {

        String sql = "INSERT INTO `order`(createDate,totalPrice,state,customer_id) VALUES (?,?,?,?)" ;
        // close auto commit
        conn.setAutoCommit(false);

        pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ;
        pstmt.setTimestamp(1, order.getCreateDate());
        pstmt.setFloat(2, order.getTotalPrice());
        pstmt.setInt(3, order.getState());
        pstmt.setLong(4, order.getCustomerId());
        pstmt.executeUpdate();

        int autoInckey = -1;
        ResultSet rs = pstmt.getGeneratedKeys(); // 获取结果
        if (rs.next()) {
            autoInckey = rs.getInt(1);// 取得ID
        } else {
            // throw an exception from here
            return false;
        }

        String insertOrderItem = "INSERT INTO orderItem(product_id,order_id,quantity) VALUES (?,?,?)";
        pstmt = conn.prepareStatement(insertOrderItem) ;
        for(OrderItem orderItem:order.getOrderItems()){
            pstmt.setLong(1, orderItem.getProduct().getId());
            pstmt.setLong(2, (long) autoInckey);
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.addBatch();
        }

        pstmt.executeBatch();
        conn.commit();
        conn.close();

        return true;
    }
}
