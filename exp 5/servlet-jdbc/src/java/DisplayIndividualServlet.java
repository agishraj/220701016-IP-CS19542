/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author CAROLINE
 */
public class DisplayIndividualServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("book_name");

        response.setContentType("text/html"); 
        response.setCharacterEncoding("UTF-8"); 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "Suja@2004");

            String sql = "SELECT * FROM BOOK WHERE TITLE = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookName);

            ResultSet rs = stmt.executeQuery();

            response.getWriter().write("<html><head><style>");
            response.getWriter().write("p { margin: 10px 0; }");
            response.getWriter().write("div {margin: 30px; padding: 30px; border: 1px solid #ccc; border-radius: 5px; background-color: #f9f9f9; }");
            response.getWriter().write("</style></head><body>");
            response.getWriter().write("<h2>Book Information</h2>");
            
            if (rs.next()) {
                response.getWriter().write("<div>");
                response.getWriter().write("<p><b>Book Name:</b> " + rs.getString("TITLE") + "</p>");
                response.getWriter().write("<p><b>Author:</b> " + rs.getString("AUTHOR") + "</p>");
                response.getWriter().write("<p><b>Publisher:</b> " + rs.getString("PUBLISHER") + "</p>");
                response.getWriter().write("<p><b>Edition:</b> " + rs.getString("EDITION") + "</p>");
                response.getWriter().write("<p><b>Price:</b> " + rs.getDouble("PRICE") + "</p>");
                response.getWriter().write("<p><b>Category:</b> " + rs.getString("CATEGORY") + "</p>");
                response.getWriter().write("</div>");
            } else {
                response.getWriter().write("<p>Book not found.</p>");
            }

            response.getWriter().write("</body></html>");

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<html><body><h2>Error retrieving book information.</h2></body></html>");
        }
    }
}
