/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author CAROLINE
 */
public class DisplayAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); 
        response.setCharacterEncoding("UTF-8"); 
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "Suja@2004");

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM BOOK";
            ResultSet rs = stmt.executeQuery(sql);

            // Start HTML document
            response.getWriter().write("<html><head><style>");
            response.getWriter().write("table { width: 100%; border-collapse: collapse; }");
            response.getWriter().write("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
            response.getWriter().write("th { background-color: #f2f2f2; }");
            response.getWriter().write("</style></head><body>");
            response.getWriter().write("<h2>All Books Information</h2>");
            response.getWriter().write("<table>");
            response.getWriter().write("<tr><th>Book Name</th><th>Author</th><th>Publisher</th><th>Edition</th><th>Price</th><th>Category</th></tr>");
            
            while (rs.next()) {
                response.getWriter().write("<tr>");
                response.getWriter().write("<td>" + rs.getString("TITLE") + "</td>");
                response.getWriter().write("<td>" + rs.getString("AUTHOR") + "</td>");
                response.getWriter().write("<td>" + rs.getString("PUBLISHER") + "</td>");
                response.getWriter().write("<td>" + rs.getString("EDITION") + "</td>");
                response.getWriter().write("<td>" + rs.getDouble("PRICE") + "</td>");
                response.getWriter().write("<td>" + rs.getString("CATEGORY") + "</td>");
                response.getWriter().write("</tr>");
            }
            
            response.getWriter().write("</table>");
            response.getWriter().write("</body></html>");

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<html><body><h2>Error retrieving all books information.</h2></body></html>");
        }
    }
}
