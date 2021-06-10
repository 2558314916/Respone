package com.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author mucd
 * 2021年06月04日  下午 04:01
 */

@WebServlet(value = "/out")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取绝对路径.通过servletContext()
        String path= this.getServletContext().getRealPath("abc.txt");
        //设置请求的头信息，第一参数：头的名称,第二参数：头值，URLEncoder.encode()转编码值
        response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(path.substring(path.lastIndexOf("\\")+1), StandardCharsets.UTF_8) );
        try (FileInputStream in = new FileInputStream(path)) {
            byte[] img = new byte[1024];
            int length;
            while ((length = in.read(img, 0, img.length)) != -1) {
                response.getOutputStream().write(img, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
