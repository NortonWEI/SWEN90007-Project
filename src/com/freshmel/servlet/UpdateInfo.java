package com.freshmel.servlet;

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

@WebServlet("/updateInfo")
public class UpdateInfo extends HttpServlet {

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
        }
    }
}
