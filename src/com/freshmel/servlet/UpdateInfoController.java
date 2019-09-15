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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller responsible for updating user information action
 * */

@WebServlet("/updateInfo")
public class UpdateInfoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/updateInfo");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("type");
        if (type.equals("customer")){
            Customer customer = (Customer) session.getAttribute("customer");
            customer.setFirstName(firstName);
            customer.setLasteName(lastName);
            customer.setPhoneNumber(phoneNumber);
            CustomerService customerService = new CustomerService();
            try {
                if (customerService.updateInfo(customer)){
                    req.getRequestDispatcher("profile.jsp").forward(req, resp);
                }else{
                    req.setAttribute("info", "update Info faile");
                    req.setAttribute("redirectURL", "profile.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("info", "Database error!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        }else if (type.equals("vender")){
            Vender vender = (Vender) session.getAttribute("vender");
            vender.setFirstName(firstName);
            vender.setLasteName(lastName);
            vender.setPhoneNumber(phoneNumber);
            VenderService venderService = new VenderService();
            try {
                if (venderService.updateInfo(vender)){
                    req.getRequestDispatcher("profile.jsp").forward(req, resp);
                }else{
                    req.setAttribute("info", "update Info faile");
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
}
