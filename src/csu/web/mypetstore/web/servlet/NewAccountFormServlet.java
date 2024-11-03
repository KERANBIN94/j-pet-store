package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewAccountFormServlet extends HttpServlet {

    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/Account/NewAccountForm.jsp";
    private AccountService accountService;
    private Account account;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeatedPassword");

        // 验证输入
        if (username == null || username.isEmpty()) {
            req.setAttribute("errorMsg", "用户名不能为空");
            req.getRequestDispatcher("NewAccountForm").forward(req, resp);
            return;
        }

        if (email == null || email.isEmpty()) {
            req.setAttribute("errorMsg", "邮箱不能为空");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
            return;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("errorMsg", "密码不能为空");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
            return;
        }

        if (!password.equals(repeatedPassword)) {
            req.setAttribute("errorMsg", "两次密码不匹配");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
            return;
        }

        // 创建新用户
        accountService = new AccountService();
        account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        // 若用户已存在
        if (accountService.usernameExists(username)) {
            req.setAttribute("errorMsg", "用户名已存在");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
            return;
        }

        // 保存用户账户
        accountService.createAccount(account);

        // 注册成功，重定向到成功页面
        resp.sendRedirect("mainForm");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 直接转发到注册页面
        req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
    }
}
