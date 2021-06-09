package com.test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author mucd
 * 2021年06月09日  上午 09:42
 */
@WebServlet("/attr")
public class Get extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("okokokokoko");
        ServletContext servletContext = getServletContext();

        System.out.println("这是servletContext"+servletContext.getAttribute("name"));
        Enumeration attributeNames = req.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            Object o = attributeNames.nextElement();
            System.out.println(req.getAttribute((String) o));
        }
        System.out.println("这是request"+req.getAttribute("name2"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
