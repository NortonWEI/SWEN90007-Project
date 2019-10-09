package com.freshmel.pageController;

import com.freshmel.model.Customer;
import com.freshmel.model.Vender;
import com.freshmel.service.CustomerService;
import com.freshmel.service.VenderService;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 * Controller responsible for saving photo to server and returning the updated photo to frontend
 * */

@WebServlet("/updatePhoto")
public class UpdatePhotoController extends HttpServlet {
    public String photoname;
    public String filePath;
    public String photo;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/photoUpload");

        HttpSession session = req.getSession();
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),req,resp);
            smart.upload();
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        Random ra = new Random();
        Date date = new Date();
        if(smart.getFiles().getSize()>0) {
            photoname = ra.nextInt(2000000) + date.getTime() + "."+ smart.getFiles().getFile(0).getFileExt();
            filePath = super.getServletContext().getRealPath("/upload/photo/") + "/" + photoname;
            photo =  photoname;
            try {
                System.out.println(filePath);
                smart.getFiles().getFile(0).saveAs(filePath);
            } catch (SmartUploadException e) {
                System.out.println("&&&&&&&&&save image failed");
                e.printStackTrace();
            }

            String type = (String) session.getAttribute("type");
            if (type.equals("customer")){
                Customer customer = (Customer) session.getAttribute("customer");
                customer.setPhoto(photo);
                CustomerService customerService = new CustomerService();
                try {
                    if (customerService.updatePhoto(customer)){
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
            }else if (type.equals("vender")){
                Vender vender = (Vender) session.getAttribute("vender");
                vender.setPhoto(photo);
                VenderService venderService = new VenderService();
                try {
                    if (venderService.updatePhoto(vender)){
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
        }else{
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }
}
