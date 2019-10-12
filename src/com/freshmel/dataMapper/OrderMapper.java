package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Address;
import com.freshmel.model.Order;
import com.freshmel.model.OrderItem;
import com.freshmel.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        // open transaction, close auto commit
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

    /**
     * find order by customer id
     * @param customerId with email and password.
     * @return if the customer in database return whole information of this customer
     *         if the customer not in database return null
     * */
    public List<Order> findByCustomerId(Long customerId) throws SQLException {
        String sql = "SELECT id,createDate,totalPrice,state FROM `order` WHERE customer_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1,customerId);
        ResultSet rs = pstmt.executeQuery();
        List<Order> orders = new ArrayList<Order>();
        while (rs.next()){
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setId(rs.getLong(1));
            order.setCreateDate(rs.getTimestamp(2));
            order.setTotalPrice(rs.getFloat(3));
            order.setState(rs.getInt(4));

            order.setOrderItems(loadOrderItems(order.getId()));
            order.setAddress(loadAddress(customerId));
            orders.add(order);
        }

        return orders;
    }

    public List<OrderItem> loadOrderItems(Long orderId) throws SQLException {
        String sql = "SELECT product_id,quantity FROM orderItem WHERE order_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1,orderId);
        ResultSet rs = pstmt.executeQuery();

        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        ProductMapper productMapper = new ProductMapper();
        while (rs.next()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProduct(productMapper.findByProductID(rs.getLong(1)));
            orderItem.setQuantity(rs.getInt(2));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public Address loadAddress(Long customerId) throws SQLException {
        String sql = "SELECT line1,line2,line3,suburb,state,postCode FROM customer WHERE id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1,customerId);
        ResultSet rs = pstmt.executeQuery() ;
        Address address = new Address();
        if (rs.next()) {
            address.setLine1(rs.getString(1));
            address.setLine2(rs.getString(2));
            address.setLine3(rs.getString(3));
            address.setSuburb(rs.getString(4));
            address.setState(rs.getString(5));
            address.setPostCode(rs.getString(6));
        }
        return address;
    }

    /**
     * update order state
     * @param order
     * @return if update successfully return true
     *         if update failed return false
     * */
    public boolean updateOrderState(Order order) throws SQLException {
        String sql = "UPDATE `order` SET state=? WHERE id=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setInt(1, order.getState());
        pstmt.setLong(2, order.getId());
        return pstmt.executeUpdate() > 0 ;
    }
}
