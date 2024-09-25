package com.spring.demo.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // setup connection variables
        String user = "contact";
        String pass = "contact";

        String jdbcUrl = "jdbc:mysql://localhost:3306/school_record?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        // get the connection to database
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database: " + jdbcUrl);
            Class.forName(driver);//load the JDBC Driver

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

            out.println("Connection Successful...");

            myConn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }

    }
}
