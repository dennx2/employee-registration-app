

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
@WebServlet("/ImportRecords")
public class ImportRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportRecords() {
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
			
			// create 10 sample records
			String[] record1 = {"1", "John", "IT", "Developer"};
			String[] record2 = {"2", "Mary", "Accounting", "Junior Accountant"};
			String[] record3 = {"3", "Keith", "HR", "Recruiter"};
			String[] record4 = {"4", "Ryan", "Accounting", "Manager"};
			String[] record5 = {"5", "Tom", "IT", "Manager"};
			String[] record6 = {"6", "Pat", "IT", "Developer"};
			String[] record7 = {"7", "Chris", "Design", "UI Designer"};
			String[] record8 = {"8", "Larry", "Marketing", "Strategist"};
			String[] record9 = {"9", "Alex", "HR", "Manager"};
			String[] record10 = {"10", "Claire", "Design", "UX Designer"};
			
			String[][] records = {record1, record2, record3, record4, record5, 
					record6, record7, record8, record9, record10
			};
			
			// insert sample records with prepared statement
			PreparedStatement pst = conn.prepareStatement("insert into emp values (?, ?, ?, ?)");
			
			for (String[] record : records) {
				pst.setString(1, record[0]); // replace the first question mark with...
				pst.setString(2, record[1]);
				pst.setString(3, record[2]);
				pst.setString(4, record[3]);
				
				pst.executeUpdate();
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
		
		out.println("Records Import Successful!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
