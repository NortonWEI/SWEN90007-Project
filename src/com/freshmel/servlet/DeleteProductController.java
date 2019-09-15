package com.freshmel.servlet;

import com.freshmel.model.Product;
import com.freshmel.service.VenderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller responsible for vendor deleting product item from its personal space action
 * */

@WebServlet("/deleteProduct")
public class DeleteProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        Long id = Long.parseLong(idString);
        Product product = new Product();
        product.setId(id);
        VenderService venderService = new VenderService();
        try {
            if (venderService.deleteProduct(product)){
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            }else{
                req.setAttribute("info", "delete product fail");
                req.setAttribute("redirectURL", "product.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
            } catch (SQLException e1) {
            e1.printStackTrace();
            req.setAttribute("info", "Database error!");
            req.setAttribute("redirectURL", "index.jsp");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }

    }
}
