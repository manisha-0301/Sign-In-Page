import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    private static final long serialVersionUID = 1L;
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/user_detail";
        String username = "root";
        String password = "Mimanisha03@";

        // Retrieve form data from request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String usernameValue = request.getParameter("username");
        String passwordValue = request.getParameter("password");

        // SQL query to insert data into the table
        String sql = "INSERT INTO user_table (name, email, username, password) VALUES (?, ?, ?, ?)";

        try (
            // Establish database connection
            Connection connection = DriverManager.getConnection(url, username, password);
            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            // Set values for the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, usernameValue);
            preparedStatement.setString(4, passwordValue);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rowsAffected > 0) {
                out.println("<html><body><h2>User registered successfully!</h2></body></html>");
            } else {
                out.println("<html><body><h2>Failed to register user.</h2></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
