package NewPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class Registration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter=resp.getWriter();
		
		Connection CON=null;
		PreparedStatement PMT=null;
		
		try {
			Driver dbDriver=new Driver();
			DriverManager.registerDriver(dbDriver);
			
			String dburlString="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
			CON=DriverManager.getConnection(dburlString);
			
			String queryString="insert into information values(?,?,?,?,?,?)";
			PMT=CON.prepareStatement(queryString);
			
			PMT.setString(1, req.getParameter("username"));
			PMT.setString(2, req.getParameter("password"));
			PMT.setString(3, req.getParameter("firstname"));
			PMT.setString(4, req.getParameter("lastname"));
			PMT.setInt(5, Integer.parseInt(req.getParameter("mobile")));
			PMT.setString(6, req.getParameter("email"));
			
			int res=PMT.executeUpdate();
			
			if (res!=0) {
				resp.sendRedirect("NewLogin.html");
			}else {
				printWriter.println("Not Registered Try Again");
			}
			
			if (CON!=null) {
				CON.close();
			}
			if (PMT!=null) {
				PMT.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
