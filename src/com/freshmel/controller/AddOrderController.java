package com.freshmel.controller;

import com.freshmel.dataMapper.OrderMapper;
import com.freshmel.model.Order;
import com.freshmel.model.OrderItem;
import com.freshmel.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/addOrder")
public class AddOrderController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        Date date = new Date();
        order.setCreateDate(new Timestamp(date.getTime()));
        order.setCustomerId((long) 4);
        order.setState(0);
        order.setTotalPrice((float) 99.90);

        OrderItem orderItem1 = new OrderItem();
        Product product1 = new Product();
        product1.setId((long) 5);
        orderItem1.setProduct(product1);
        orderItem1.setQuantity(4);

        OrderItem orderItem2 = new OrderItem();
        Product product2 = new Product();
        product2.setId((long) 6);
        orderItem2.setProduct(product2);
        orderItem2.setQuantity(3);

        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);


        OrderMapper orderMapper = new OrderMapper();
        try {
            orderMapper.insert(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
