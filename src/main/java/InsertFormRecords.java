

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImportRecords
 */
@WebServlet("/InsertFormRecords")
public class InsertFormRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFormRecords() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PrintWriter out = response.getWriter();
		
		try {
			// load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// create connection
			String dbURL = "jdbc:mysql://localhost:3306/as2_jaydenn";
			String username = "root";
			String password = "root";
			
			// establish connection to db
			conn = DriverManager.getConnection(dbURL, username, password);
			
			// check connection status
//			out.println("Is my connection closed? " + conn.isClosed());
			
			// read form input
			String empNumber = request.getParameter("empNumber");
			String empName = request.getParameter("empName");
			String jobTitle = request.getParameter("jobTitle");
			String dep = request.getParameter("dep");
			
//			System.out.println(dep);
			
			// insert sample records with prepared statement
			PreparedStatement pst = conn.prepareStatement("insert into emp values (?, ?, ?, ?)");
			pst.setString(1, empNumber); // replace the first question mark with...
			pst.setString(2, empName);
			pst.setString(3, dep);
			pst.setString(4, jobTitle);
			
			pst.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		out.println("Submission Successful!");
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
