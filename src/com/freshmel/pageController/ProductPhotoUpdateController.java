package com.freshmel.pageController;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

/**
 * Controller responsible for save product photo to server and return the path to front-end
 * */

@WebServlet("/productPhotoUpload")
public class ProductPhotoUpdateController extends HttpServlet {
    public String photoname;
    public String filePath;
    public String photo;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/productPhotoUpload");

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
            photoname = ra.nextInt(2000000) + date.getTime() + "." + smart.getFiles().getFile(0).getFileExt();
            filePath = super.getServletContext().getRealPath("/upload/photo/") + "/" + photoname;
            photo = photoname;
            try {
                System.out.println(filePath);
                smart.getFiles().getFile(0).saveAs(filePath);
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                out.println(photo);
            } catch (SmartUploadException e) {
                System.out.println("&&&&&&&&&save image failed");
                e.printStackTrace();
            }
        }
    }
}
