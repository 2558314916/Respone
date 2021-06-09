package com.test;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author mucd
 * 2021年06月04日  下午 03:59
 */
@WebServlet(value = "/vCode")
public class ValidateCodeTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",0);
        HttpSession session = req.getSession();
        //高宽 数字个数 干扰线个数
        ValidateCode vCode = new ValidateCode(130,50,4,100);
        String code = vCode.getCode();
        session.setAttribute("vcodes",code);
        //req.setAttribute("codes",code);
        vCode.write(resp.getOutputStream());
        System.out.println("Vcode = " + code);
    }
}
