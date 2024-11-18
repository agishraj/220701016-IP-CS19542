/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author CAROLINE
 */

public class InsertBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html"); 
        response.setCharacterEncoding("UTF-8"); 
        
        String bookName = request.getParameter("book_name");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String edition = request.getParameter("edition");
        String price = request.getParameter("price");
        String category = request.getParameter("category");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Suja@2004");
            
            String sql = "INSERT INTO BOOK (TITLE, AUTHOR, PUBLISHER, EDITION, PRICE, CATEGORY) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookName);
            stmt.setString(2, author);
            stmt.setString(3, publisher);
            stmt.setString(4, edition);
            stmt.setDouble(5, Double.parseDouble(price));
            stmt.setString(6, category);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            response.getWriter().write("Book information inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error inserting book information.");
        }
    }
}
