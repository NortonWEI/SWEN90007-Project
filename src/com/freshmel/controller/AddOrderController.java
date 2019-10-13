package com.freshmel.controller;

import com.freshmel.dataMapper.CartMapper;
import com.freshmel.dataMapper.OrderMapper;
import com.freshmel.dataMapper.ProductMapper;
import com.freshmel.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/addOrder")
public class AddOrderController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Order order = new Order();
//        Date date = new Date();
//        order.setCreateDate(new Timestamp(date.getTime()));
//        order.setCustomerId((long) 4);
//        order.setState(0);
//        order.setTotalPrice((float) 99.90);
//
//        OrderItem orderItem1 = new OrderItem();
//        Product product1 = new Product();
//        product1.setId((long) 5);
//        orderItem1.setProduct(product1);
//        orderItem1.setQuantity(4);
//
//        OrderItem orderItem2 = new OrderItem();
//        Product product2 = new Product();
//        product2.setId((long) 6);
//        orderItem2.setProduct(product2);
//        orderItem2.setQuantity(3);
//
//        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
//        orderItems.add(orderItem1);
//        orderItems.add(orderItem2);
//
//        order.setOrderItems(orderItems);
//
//
//        OrderMapper orderMapper = new OrderMapper();
//        try {
//            orderMapper.insert(order);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        List<Cart> carts = customer.getCarts();

        if (carts.size() == 0){
            req.setAttribute("info", "cart is empty, please go to add products into cart!");
            req.setAttribute("redirectURL", "/shop");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            System.out.println("--------");
            return;
        }

        Order order = new Order();
        Date date = new Date();
        order.setCreateDate(new Timestamp(date.getTime()));
        order.setCustomerId(customer.getId());
        order.setState(1);

        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        Float totalPrice = (float)0;
        for (Cart cart : carts){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cart.getProduct());
            orderItem.setQuantity(cart.getQuantity());

            totalPrice = totalPrice + cart.getQuantity() * cart.getProduct().getPrice();
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        OrderMapper orderMapper = new OrderMapper();
        try {
            if (orderMapper.insert(order)){
                // minus product quantity
//                ProductMapper productMapper = new ProductMapper();
//                for (Cart cart : carts){
//                    Product product = cart.getProduct();
//                    // * remember to deal with quantity num > inventory
//                    product.setInventory(product.getInventory() - cart.getQuantity());
//                    productMapper.updateProductQuantity(product);
//                }

                CartMapper cartMapper = new CartMapper();
                //delete cart
                cartMapper.deleteByCustomerId(customer.getId());
                customer.getCarts().clear();
                req.getRequestDispatcher("/ListCustomerOrder").forward(req, resp);
            }else{
                req.setAttribute("info", "check out fail");
                req.setAttribute("redirectURL", "/cart");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("info", e.getMessage());
            req.setAttribute("redirectURL", "index.jsp");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }
    }
}
