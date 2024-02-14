import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Validate01
 */
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String DB_URL = "jdbc:mysql://localhost:3306/user_detail";
	    String DB_USER = "root";
	    String DB_PASSWORD = "Mimanisha03@";
	    

	    try{
	        Connection connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        PreparedStatement ps=connect.prepareStatement("SELECT * FROM user_detail.details WHERE password=? AND username=?");
	            ps.setString(1, DB_PASSWORD);
	            ps.setString(2, DB_USER);

	            ResultSet rs = ps.executeQuery();

	            if(rs.next()){
	                System.out.println("Login successful!");
	            }
	            else{
	                System.out.println("Incorrect Credential");
	            }
	        }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	}

}
