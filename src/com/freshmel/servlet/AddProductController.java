package com.freshmel.servlet;

import com.freshmel.model.Product;
import com.freshmel.model.Vender;
import com.freshmel.service.VenderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller responsible for vender adding new product to system personal space action
 * */

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String photo = req.getParameter("photo");
        if(photo == null | photo.equals("")){
            photo = "defaultProduct.jpg";
        }
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        String stateString = req.getParameter("state");
        String priceString = req.getParameter("price");
        String inventoryString = req.getParameter("inventory");
        Float price = Float.parseFloat(priceString);
        Integer state = Integer.parseInt(stateString);
        Integer inventory = Integer.parseInt(inventoryString);
        HttpSession session = req.getSession();
        Vender vender = (Vender) session.getAttribute("vender");

        Product product = new Product();
        product.setVenderId(vender.getId());
        product.setPhoto(photo);
        product.setName(name);
        product.setDescription(description);
        product.setType(type);
        product.setPrice(price);
        product.setState(state);
        product.setInventory(inventory);

        VenderService venderService = new VenderService();
        try {
            if (venderService.addNewProduct(product)){
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            }else{
                req.setAttribute("info", "add product fail");
                req.setAttribute("redirectURL", "profile.jsp");
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
