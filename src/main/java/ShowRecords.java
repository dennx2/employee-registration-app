

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
@WebServlet("/ShowRecords")
public class ShowRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRecords() {
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
			
			// select records
			Statement statement = conn.createStatement();
			String query = "select * from emp";
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				out.println(rs.getString("empNo") + "\t" + rs.getString("empName") + "\t" +
							rs.getString("department")+ "\t\t" + rs.getString("empJob"));
			}
			
			
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
