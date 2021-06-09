package com.test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author mucd
 * 2021年06月09日  上午 09:29
 */
@WebServlet(value = "/mime")
public class GetMiMe extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        String mimeType = servletContext.getMimeType("Login.java");
        System.out.println("mimeType = " + mimeType);
        servletContext.setAttribute("name","hahaha");
        req.setAttribute("name2","asdaskl");
        System.out.println(req.getServletContext());
        PrintWriter writer = resp.getWriter();
        writer.println("<a href = 'attr'>click</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
