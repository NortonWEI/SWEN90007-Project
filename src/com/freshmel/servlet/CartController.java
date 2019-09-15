package com.freshmel.servlet;

import com.freshmel.model.Customer;
import com.freshmel.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller responsible for acquiring customer history cart items after login action
 * */

@WebServlet("/cart")
public class CartController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null ){
            req.setAttribute("info", "you are a vender cannot add cart, please use customer account!!!");
            req.setAttribute("redirectURL", "/shop");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }
        CustomerService customerService = new CustomerService();
        try {
            customer.setCarts(customerService.getCarts(customer.getId()));
            req.setAttribute("carts", customer.getCarts());
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
            Customer.ADD_CART = 0;
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("info", "Database error!");
            req.setAttribute("redirectURL", "index.jsp");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }
    }
}
