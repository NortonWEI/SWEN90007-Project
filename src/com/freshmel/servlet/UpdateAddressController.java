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

@WebServlet("/updateAddress")
public class UpdateAddressController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String line1 = req.getParameter("line1");
        String line2 = req.getParameter("line2");
        String line3 = req.getParameter("line3");
        String suburb = req.getParameter("suburb");
        String state = req.getParameter("state");
        String postCode = req.getParameter("postCode");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        customer.getAddresses().setFirstName(firstName);
        customer.getAddresses().setLastName(lastName);
        customer.getAddresses().setPhone(phone);
        customer.getAddresses().setLine1(line1);
        customer.getAddresses().setLine2(line2);
        customer.getAddresses().setLine3(line3);
        customer.getAddresses().setSuburb(suburb);
        customer.getAddresses().setState(state);
        customer.getAddresses().setPostCode(postCode);

        CustomerService customerService = new CustomerService();
        try {
            if (customerService.updateAddress(customer.getAddresses())){
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
            }else {
                req.setAttribute("info", "update fail");
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
