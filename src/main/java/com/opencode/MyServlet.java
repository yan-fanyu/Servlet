package com.opencode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class MyServlet extends HttpServlet {
    static int count;
    // 创建 Servlet 的时候进行初始化
    @Override
    public void init() throws ServletException {
        System.out.println("Servlet init");
        count = 0;
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

    // 实验二：根据数据库 校验 登录信息
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        System.out.println(username);
        System.out.println(gender);

        String dbUser = "root";
        String dbPass = "root";
        // todo 注意：com.mysql.jdbc.Driver 已经废弃
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/servlet";
        Connection coon = null;
        String query = "select * from user where name=? and gender=?";
        PrintWriter writer = response.getWriter();
        try {
            Class.forName(dbDriver);
            coon = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement pst = coon.prepareStatement(query);
            pst.setObject(1, username);
            pst.setObject(2, gender);
            System.out.println(query);

            System.out.println("HAHHA");
            ResultSet resultSet = pst.executeQuery();
            if(!resultSet.isBeforeFirst()){
                writer.println("登录失败-------->NO");
            }else{
                writer.println("登录成功-------->OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("有链接");
    }

    // 实验一：统计访问次数
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        count++;

        PrintWriter writer = resp.getWriter();
        writer.println(
                "<html>\n" +
                        "<body>\n" +
                        "<h1>" + "访问次数: " +count + "</h1>\n" +
                        "</html>"
        );
    }
}
