package com.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mucd
 * 2021年06月08日  上午 08:50
 */
@WebServlet(value = "/redirect")
public class Redirect extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置utf-8编码
        resp.setContentType("text/html;charset=utf-8");
        //内容输出到客户端
        resp.getWriter().println("<h1>H!H!H!H!H!H!H!H!H!H!H!H</h1>");
        resp.getWriter().write("<h2>这是H2</h2>");
        //resp.setStatus(302);
        resp.setHeader("refresh","2;http://www.baidu.com");
        resp.setDateHeader("time",123123);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
