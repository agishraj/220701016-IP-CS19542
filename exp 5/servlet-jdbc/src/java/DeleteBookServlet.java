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


public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); 
        response.setCharacterEncoding("UTF-8"); 
        
        String bookName = request.getParameter("book_name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "Suja@2004");

            String sql = "DELETE FROM BOOK WHERE TITLE=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookName);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                response.getWriter().write("Book deleted successfully!");
            } else {
                response.getWriter().write("Book not found.");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error deleting book information.");
        }
    }
}
