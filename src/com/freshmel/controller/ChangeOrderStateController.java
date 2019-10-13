package com.freshmel.controller;

import com.freshmel.dataMapper.OrderMapper;
import com.freshmel.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/changeOrderState")
public class ChangeOrderStateController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderIdString = req.getParameter("orderId");
        String orderStateString = req.getParameter("orderState");
        Long orderId = Long.parseLong(orderIdString);
        Integer orderState = Integer.parseInt(orderStateString);
        OrderMapper orderMapper = new OrderMapper();
        Order order = new Order();
        order.setId(orderId);
        order.setState(orderState);

        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("type");


        try {
            if (orderMapper.updateOrderState(order)){
                if (type.equals("vender")){
                    resp.sendRedirect("/ListVenderOrder");
                }else {
                    resp.sendRedirect("/ListCustomerOrder");
                }
            }else{
                req.setAttribute("info", "change state fail");
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
