package com.freshmel.servlet;

import com.freshmel.model.Cart;
import com.freshmel.model.Customer;
import com.freshmel.model.Product;
import com.freshmel.service.CustomerService;
import com.freshmel.unitOfWork.CartUOW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addCart")
public class AddCartController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");
        String quantityString = req.getParameter("quantity");
        Long productId = Long.parseLong(productIdString);
        Integer quantity = Integer.parseInt(quantityString);
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Product product = new Product();
        product.setId(productId);
//        if (Customer.ADD_CART == 0) {
//            CartUOW.newCurrent();
//        }
        CartUOW.newCurrent();
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setCustomerId(customer.getId());
        try {
            CartUOW.getCurrent().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        CustomerService customerService = new CustomerService();
//        try {
//            if (customerService.addToCart(cart)){
        resp.sendRedirect("/shop");
        Customer.ADD_CART = 1;
//            } else {
//                req.setAttribute("info", "add cart fail");
//                req.setAttribute("redirectURL", "/shop");
//                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            req.setAttribute("info", "Database error!");
//            req.setAttribute("redirectURL", "index.jsp");
//            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
//        }
    }
}
