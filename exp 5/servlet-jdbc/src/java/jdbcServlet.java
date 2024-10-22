/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import jakarta.servlet.annotation.WebServlet;


/**
 *
 * @author caroline
 */
@WebServlet("/jdbcServlet")
public class jdbcServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        String accno=request.getParameter("accno");
        String title=request.getParameter("title");
        String author=request.getParameter("author");
        String publisher=request.getParameter("publisher");
        String edition=request.getParameter("edition");
        String price=request.getParameter("price");
        String action = request.getParameter("action"); 

        String url="jdbc:mysql://localhost:3306/jdbc";
        String username="root";
        String password="Arthey@1274";
         
        Connection conn=null;
        PreparedStatement stmt=null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,username,password);
      
             if("insert".equals(action)){
             
            String sql="Insert into book(accno,title,author,publisher,edition,price)values(?,?,?,?,?,?)";
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(accno));
            stmt.setString(2,title);
            stmt.setString(3,author);
            stmt.setString(4, publisher);
            stmt.setString(5,edition);
            stmt.setBigDecimal(6,new BigDecimal(price));
            
            stmt.executeUpdate();
            response.getWriter().println("Book inserted successfully");
            
            }
            else if("update".equals(action))
            {
               

                String sql="update book set title=?,author=?,publisher=?,edition=?,price=? where accno=?";
                stmt=conn.prepareStatement(sql);
                stmt.setString(1,title);
                stmt.setString(2,author);
                stmt.setString(3,publisher);
                stmt.setString(4,edition);
                stmt.setBigDecimal(5, new BigDecimal(price));
                stmt.setInt(6, Integer.parseInt(accno));
                
                int rowsUpdated=stmt.executeUpdate();
                 if (rowsUpdated > 0) {
                            response.getWriter().write("{\"status\":\"success\",\"message\":\"Book updated successfully\"}");
                  } 
                 else {
                            response.getWriter().write("{\"status\":\"error\",\"message\":\"No book found with the provided account number\"}");
                 }
                    }
             else if ("delete".equals(action)) {
                String sql = "DELETE FROM book WHERE accno=?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(accno));
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    response.getWriter().write("{\"status\":\"success\",\"message\":\"Book deleted successfully\"}");
                } else {
                    response.getWriter().write("{\"status\":\"error\",\"message\":\"No book found with the provided account number\"}");
                }
             }
               
           if("fetchAll".equals(action))
           {
              String sql = "SELECT accno, title, author, publisher, edition, price FROM book";
              stmt=conn.prepareStatement(sql);
              ResultSet rs=stmt.executeQuery();
              StringBuilder json = new StringBuilder("[");
              
              while(rs.next())
              {
                  if(json.length()>1){
                      json.append(",");
                  }
                        json.append(String.format("{\"accno\":\"%d\", \"title\":\"%s\", \"author\":\"%s\", \"publisher\":\"%s\", \"edition\":\"%s\", \"price\":\"%s\"}",
                    rs.getInt("accno"), rs.getString("title"), rs.getString("author"),
                    rs.getString("publisher"), rs.getString("edition"), rs.getString("price")));
              }
              json.append("]");
              
              response.getWriter().write(json.toString());
              rs.close();
           }
            else if (request.getMethod().equalsIgnoreCase("GET") && accno != null) {
            String sql = "SELECT title, author, publisher, edition, price FROM book WHERE accno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(accno));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
           
                String json = String.format("{\"title\":\"%s\", \"author\":\"%s\", \"publisher\":\"%s\", \"edition\":\"%s\", \"price\":\"%s\"}",
                                            rs.getString("title"), rs.getString("author"), rs.getString("publisher"),
                                            rs.getString("edition"), rs.getString("price"));
                response.getWriter().write(json);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"status\":\"error\",\"message\":\"No book found with the provided account number\"}");
            }
              rs.close();
        } 

        }
    catch (SQLException e) {
    e.printStackTrace();
    response.getWriter().println("SQL Error: " + e.getMessage());
} 
  catch (NumberFormatException e) {
    e.printStackTrace();
    response.getWriter().println("Number Format Error: " + e.getMessage());
} 
 catch (Exception e) {
    e.printStackTrace();
    response.getWriter().println("General Error: " + e.getMessage());
}
        
    
        finally{
             try{
                 if(stmt!=null)stmt.close();
                 if(conn!=null)conn.close();
             }
             catch(SQLException e)
             {
                 e.printStackTrace();
             }
        }
       
    }
 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
