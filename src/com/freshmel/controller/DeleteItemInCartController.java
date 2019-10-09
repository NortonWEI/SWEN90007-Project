package com.freshmel.controller;

import com.freshmel.model.Customer;
import com.freshmel.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller responsible for customer deleting item from cart action
 * */

@WebServlet("/deleteItemInCart")
public class DeleteItemInCartController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");
        Long productId = Long.parseLong(productIdString);
        CustomerService customerService = new CustomerService();
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            if (customerService.deleteCartItem(productId,customer.getId())){
                resp.sendRedirect("/cart");
            }else{
                req.setAttribute("info", "delete fail");
                req.setAttribute("redirectURL", "/cart");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("info", "Database error!");
            req.setAttribute("redirectURL", "index.jsp");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }
    }
}
