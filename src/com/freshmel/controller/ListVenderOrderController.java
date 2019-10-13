package com.freshmel.controller;

import com.freshmel.dataMapper.OrderMapper;
import com.freshmel.model.Customer;
import com.freshmel.model.Order;
import com.freshmel.model.Vender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListVenderOrder")
public class ListVenderOrderController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Vender vender = (Vender) session.getAttribute("vender");
        OrderMapper orderMapper = new OrderMapper();
        try {
            List<Order> orders = orderMapper.findByVender(vender.getId());
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("venderOrder.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("info", "Database error!");
            req.setAttribute("redirectURL", "index.jsp");
            req.getRequestDispatcher("redirect.jsp").forward(req, resp);
        }
    }
}
