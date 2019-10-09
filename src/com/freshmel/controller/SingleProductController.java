package com.freshmel.controller;

import com.freshmel.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller responsible for getting specific product by id for product page
 * */

@WebServlet("/singleProduct")
public class SingleProductController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        Long id = Long.parseLong(idString);
        ProductService productService = new ProductService();
        try {
            req.setAttribute("product",productService.getByProductId(id));
            req.getRequestDispatcher("/product-single.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("info", "Database error!");
            req.setAttribute("redirectURL", "index.jsp");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }
    }
}
