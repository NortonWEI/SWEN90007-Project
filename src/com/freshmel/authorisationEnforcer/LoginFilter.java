package com.freshmel.authorisationEnforcer;

import com.freshmel.model.Customer;
import com.freshmel.model.Vender;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login Filter to block visitors to go places that need to login before
 * */

@WebFilter(filterName="LoginFilter",urlPatterns={"/cart","/addCart","/addProduct","/deleteItemInCart","/deleteProduct","/logout","/productPhotoUpload","/updateAddress","/updateInfo","/updatePhoto","/updateProduct","/product.jsp","/cart.jsp","/profile.jsp"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        Vender vender = (Vender) session.getAttribute("vender");
        Customer customer = (Customer) session.getAttribute("customer");
        if((vender == null) && (customer == null)) {
            httpRequest.setAttribute("info", "Please log in first!");
            httpRequest.setAttribute("redirectURL", "index.jsp");
            httpRequest.getRequestDispatcher("redirect.jsp").forward(servletRequest, servletResponse);
            System.out.print("not log in but want to visit!");
        }

        // pass the request along the filter chain
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
