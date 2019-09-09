package com.freshmel.servlet;

import com.freshmel.model.Customer;
import com.freshmel.model.Vender;
import com.freshmel.service.CustomerService;
import com.freshmel.service.VenderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/register");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        System.out.println(email);
        System.out.println(password);
        System.out.println(type);

        // do vender register
        if (type.equals("vender")){
            VenderService venderService = new VenderService();
            Vender vender = new Vender();
            vender.setEmail(email);
            vender.setPassword(password);
            try {
                if (venderService.register(vender)){
                    // register success
                    req.setAttribute("info", "Register successfully!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }else {
                    req.setAttribute("info", "Register failed: Email exist!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }
            }catch (SQLIntegrityConstraintViolationException e){
                req.setAttribute("info", "Register failed: Email exist!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
            catch (SQLException e) {
                // SQL error
                e.printStackTrace();
                req.setAttribute("info", "Database error!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }

        }
        // do customer register
        else if(type.equals("customer")) {
            CustomerService customerService = new CustomerService();
            Customer customer = new Customer();
            customer.setEmail(email);
            customer.setPassword(password);
            try {
                if (customerService.register(customer)){
                    // register success
                    req.setAttribute("info", "Register successfully!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }else {
                    req.setAttribute("info", "Register failed: Email exist!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }
            }catch (SQLIntegrityConstraintViolationException e){
                req.setAttribute("info", "Register failed: Email exist!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
            catch (SQLException e) {
                // SQL error
                e.printStackTrace();
                req.setAttribute("info", "Database error!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        }
    }
}
