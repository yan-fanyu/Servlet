package com.opencode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    // 创建 Servlet 的时候进行初始化
    @Override
    public void init() throws ServletException {
        System.out.println("Servlet init");
        super.init();
    }

    // 在 doPost 和 doGet 之前被调用 然后将请求转发给 doPost 和 doGet
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service -> ");
        super.service(req, resp);
    }

    // Servlet 销毁
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

        System.out.println("有链接");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 拿参数 http://localhost:8080/myServlet?username="Tom"&password="123456"
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("账号: " + username);
        System.out.println("密码: " + password);
    }
}