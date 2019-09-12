package com.freshmel.servlet;

import com.freshmel.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/shop")
public class Shop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        ProductService productService = new ProductService();
        if (type != null){
            try {
                req.setAttribute("products", productService.getByType(type));
                req.getRequestDispatcher("shop.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("info", "Database error!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        }else{
            try {
                req.setAttribute("products", productService.getAllProducts());
                req.getRequestDispatcher("shop.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("info", "Database error!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        }
    }
}
