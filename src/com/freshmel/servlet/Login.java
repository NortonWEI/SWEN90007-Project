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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        System.out.println(email);
        System.out.println(password);
        System.out.println(type);
        if (type.equals("vender")){
            VenderService venderService = new VenderService();
            Vender vender = new Vender();
            vender.setEmail(email);
            vender.setPassword(password);
            try {
                vender = venderService.login(vender);
                if (vender !=null){
                    // login success
                    HttpSession session = req.getSession();
                    session.setAttribute("vender", vender);
                    session.setAttribute("type", "vender");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }else{
                    req.setAttribute("info", "email or password error!");
                    req.setAttribute("redirectURL", "index.jsp");
                    req.getRequestDispatcher("redirect.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("info", "Database error!");
                req.setAttribute("redirectURL", "index.jsp");
                req.getRequestDispatcher("redirect.jsp").forward(req, resp);
            }
        }else if (type.equals("customer")){
            CustomerService customerService = new CustomerService();
            Customer customer = new Customer();
            customer.setEmail(email);
            customer.setPassword(password);
            try {
                customer = customerService.login(customer);
                if (customer !=null){
                    // login success
                    HttpSession session = req.getSession();
                    session.setAttribute("customer", customer);
                    session.setAttribute("type", "customer");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }else{
                    req.setAttribute("info", "email or password error!");
                    req.setAttribute("redirectURL", "index.jsp");
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
