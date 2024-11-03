package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.domain.Account;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnFormServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/Account/SignOnForm.jsp";

    private String username;
    private String password;
    private String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        String userCaptcha = req.getParameter("captcha");

        HttpSession session = req.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");

        // 校验用户输入的正确性
        if (!validate() || !isCaptchaValid(userCaptcha, sessionCaptcha)) {
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            return; // 结束方法执行
        }

        AccountService accountService = new AccountService();
        Account loginAccount = accountService.getAccount(username, password);
        if (loginAccount == null) {
            this.msg = "用户名或密码错误";
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        } else {
            loginAccount.setPassword(null);
            session.setAttribute("loginAccount", loginAccount);
            if (loginAccount.isListOption()) {
                CatalogService catalogService = new CatalogService();
                List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                session.setAttribute("myList", myList);
            }
            session.setAttribute("username", username);
            resp.sendRedirect("mainForm");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 直接转发到登录页面
        req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
    }

    private boolean validate() {
        if (username == null || username.trim().isEmpty()) {
            this.msg = "用户名不能为空"; // 设置错误信息
            return false; // 用户名无效
        }
        if (username.length() > 20) {
            this.msg = "用户名过长"; // 设置错误信息
            return false; // 用户名过长
        }
        if (password == null || password.trim().isEmpty()) {
            this.msg = "密码不能为空"; // 设置错误信息
            return false; // 密码无效
        }
        if (password.length() < 6) {
            this.msg = "密码过短"; // 设置错误信息
            return false; // 密码过短
        }
        return true;
    }

    private boolean isCaptchaValid(String userCaptcha, String sessionCaptcha) {
        if (userCaptcha == null || sessionCaptcha == null) {
            this.msg = "验证码无效"; // 设置错误信息
            return false;
        }
        return userCaptcha.equalsIgnoreCase(sessionCaptcha); // 不区分大小写比较
    }
}
