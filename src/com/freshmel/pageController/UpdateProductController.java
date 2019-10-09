package com.freshmel.pageController;

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
 * Controller responsible for updating vender product information action
 * */

@WebServlet("/updateProduct")
public class UpdateProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");
        String photo = req.getParameter("photo");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        String stateString = req.getParameter("state");
        String priceString = req.getParameter("price");
        String inventoryString = req.getParameter("inventory");
        Float price = Float.parseFloat(priceString);
        Integer state = Integer.parseInt(stateString);
        Integer inventory = Integer.parseInt(inventoryString);
        Long productId = Long.parseLong(productIdString);


        Product product = new Product();
        product.setId(productId);
        product.setPhoto(photo);
        product.setName(name);
        product.setDescription(description);
        product.setType(type);
        product.setPrice(price);
        product.setState(state);
        product.setInventory(inventory);

        VenderService venderService = new VenderService();
        try {
            if (venderService.updateProduct(product)){
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            }else{
                req.setAttribute("info", "update product fail");
                req.setAttribute("redirectURL", "product.jsp");
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
