package csu.web.mypetstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


public class CaptchaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 150;
        int height = 50;

        // 创建图像
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        // 生成随机验证码
        String captcha = generateCaptcha(6);
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha); // 存储验证码到 session

        // 设置背景色
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        // 设置字体
        g.setFont(new Font("Arial", Font.BOLD, 30));

        // 设置验证码颜色
        g.setColor(Color.BLACK);
        g.drawString(captcha, 20, 35);

        // 输出图像
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        javax.imageio.ImageIO.write(bufferedImage, "png", out);
        out.flush();
        out.close();
    }

    private String generateCaptcha(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            captcha.append(chars.charAt(index));
        }
        return captcha.toString();
    }
}
