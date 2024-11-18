import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regNo = request.getParameter("regNo");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Database connection and query setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "admin");
            String query = "SELECT * FROM students WHERE reg_no=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(regNo));

            rs = ps.executeQuery();

            // Output student details along with marks
            if (rs.next()) {
                out.println("<p>Registration Number: " + rs.getInt("reg_no") + "</p>");
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Department: " + rs.getString("department") + "</p>");
                out.println("<p>Mark 1: " + rs.getInt("mark1") + "</p>");
                out.println("<p>Mark 2: " + rs.getInt("mark2") + "</p>");
                out.println("<p>Mark 3: " + rs.getInt("mark3") + "</p>");
            } else {
                out.println("<p>No student found with Registration Number " + regNo + "</p>");
            }

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                out.println("<p>Error closing resources: " + e.getMessage() + "</p>");
            }
        }
    }
}
