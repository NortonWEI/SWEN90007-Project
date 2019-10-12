package com.freshmel.controller;

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

/**
 * Controller responsible for handling password reset action
 * */

@WebServlet("/reset")
public class ResetPasswordController extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/reset");
        String type = req.getParameter("type");
        String email = req.getParameter("email");
        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");
        System.out.println(type);
        System.out.println(email);

        // do vender reset
        if (type.equals("vender")){
            VenderService venderService = new VenderService();
            try {
                if (venderService.resetPassword(email, oldPassword, newPassword)){
                    // reset success
                    req.setAttribute("info", "Reset successfully! Please use the new password to login!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }else {
                    req.setAttribute("info", "Reset failed: Email doesn't exist or your old password is wrong!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }
            }catch (SQLIntegrityConstraintViolationException e){
                req.setAttribute("info", "Reset failed: Email doesn't exist or your old password is wrong!");
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
        // do customer reset
        else if(type.equals("customer")) {
            CustomerService customerService = new CustomerService();
            try {
                if (customerService.resetPassword(email, oldPassword, newPassword)){
                    // reset success
                    req.setAttribute("info", "Reset successfully! Please use the new password to login!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }else {
                    req.setAttribute("info", "Reset failed: Email doesn't exist or your old password is wrong!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }
            }catch (SQLIntegrityConstraintViolationException e){
                req.setAttribute("info", "Reset failed: Email doesn't exist or your old password is wrong!");
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
