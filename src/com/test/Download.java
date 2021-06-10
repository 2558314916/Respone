package com.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author mucd
 * 2021年06月09日  下午 03:07
 */

@WebServlet("/download")
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取路径
        String path = this.getServletContext().getRealPath("img/1.avi");
        resp.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(path.substring(path.lastIndexOf("\\")+1), StandardCharsets.UTF_8) );
        FileInputStream fs = new FileInputStream(path);
        byte[] bytes = new byte[1024];
        int len ;
        while((len = fs.read(bytes)) != -1){
            resp.getOutputStream().write(bytes,0,len);
        }
        fs.close();
        //获取路径
/*
        String path = this.getServletContext().getRealPath("img/1.avi");
        resp.setHeader("content", "attachment;filename=" + URLEncoder.encode(path.substring(path.lastIndexOf("\\") + 1), StandardCharsets.UTF_8));
        FileInputStream fs = new FileInputStream(path);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fs.read()) != -1) {
            resp.getOutputStream().write(bytes,0,len);
        }
        fs.close();*/
    }
}
