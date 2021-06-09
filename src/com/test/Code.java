package com.test;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @author mucd
 * 2021年06月08日  下午 04:01
 */
@WebServlet(value = "/code")
public class Code extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //1.创建一对象，验证码的图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.美化图片
        //2.1填充背景色
        Graphics g = image.getGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);
        //2.2画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //随机抽取
        SecureRandom r = new SecureRandom();
        for (int i = 1; i <= 4; i++) {
            int index = r.nextInt(str.length());
            char ch = str.charAt(index);
            g.drawString(ch + "", width / 5 * i, height / 2);
        }
        //画干扰线
        g.setColor(Color.pink);
        //随机生成坐标点
        for (int i = 0; i < 5; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
        //3.将图片展示到页面中
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }
}
