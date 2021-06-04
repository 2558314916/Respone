package com.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author mucd
 * 2021年06月04日  下午 04:01
 */

public class Test {
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 5;
    // 验证码干扰线数
    private int lineCount = 150;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;
    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    /**
     * 默认构造函数,设置默认参数
     */
    public Test(){
        this.createCode();
    }

    /**
     * 图片宽高
     * @param width
     * @param height
     */
    public Test(int width,int height){
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     * 宽高 数量 干扰线数量
     * @param width
     * @param height
     * @param codeCount
     * @param lineCount
     */
    public Test(int width,int height,int codeCount,int lineCount){
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public static void main(String[] args) {
        System.out.println(new Test(260, 55, 5, 150).getCode());

    }

    private void createCode() {
        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;
        x = width / (codeCount + 2);//每个字符的宽度(左右各空出一个字符)
        fontHeight = height - 2;//字体的高度
        codeY = height - 4;
        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 生成随机数
        SecureRandom random = new SecureRandom();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体,可以修改为其它的
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
//        Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, fontHeight);
        g.setFont(font);
        for (int i = 0; i < lineCount; i++) {
            // 设置随机开始和结束坐标
            //x坐标开始
            int xs = random.nextInt(width);
            //y坐标开始
            int ys = random.nextInt(height);
            //x坐标结束
            int xe = xs + random.nextInt(width / 8);
            //y坐标结束
            int ye = ys + random.nextInt(height / 8);
            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        // randomCode记录随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        code = randomCode.toString();
    }

    public void write(String path){
        try {
            OutputStream sos = new FileOutputStream(path);
            this.write(sos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void write(OutputStream sos){
        try {
            ImageIO.write(buffImg,"png",sos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getBuffImg(){
        return buffImg;
    }
    public String getCode(){
        return code.toLowerCase();
    }
}
