package com.test;

import com.domain.User;
import com.utils.Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author mucd
 * 2021年06月05日  上午 12:47
 */
@WebServlet(value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");
        String code = req.getParameter("code");
        //校验验证码
        HttpSession session = req.getSession();
        Object vcodes = session.getAttribute("vcodes");
        System.out.println("vcodes = " + vcodes);
        System.out.println("code = " + code);

        QueryRunner queryRunner = new QueryRunner(Utils.getDataSource());
        try {
            List<User> query = queryRunner.query(
                    "select * from mucd.vcode"
                    , new BeanListHandler<>(User.class)
            );
            //验证码不为空  输入框验证码和生成的验证码作对比
            if (!code.isEmpty() && code.equalsIgnoreCase((String) vcodes)){
                for (User user:query){
                    String domainUser = user.getUsername();
                    String domainPasswd = user.getPassword();
                    if (username.equals(domainUser) && passwd.equals(domainPasswd)){
                        //登陆成功
                        System.out.println("CG");
                    }
                }
                System.out.println("CG");
            }else if (vcodes == null){
                System.out.println("填写验证码");
            }else {
                System.out.println("验证码错误");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
