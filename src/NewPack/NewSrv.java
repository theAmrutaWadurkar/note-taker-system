package NewPack;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;



public class NewSrv extends HttpServlet {
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uname=req.getParameter("uname");
		String pass=req.getParameter("pass");
		PrintWriter printWriter=resp.getWriter();
		
		printWriter.println("Hi Welcome");
		printWriter.println(req.getRequestURL());
		
		printWriter.println("Username:- "+uname);
		printWriter.println("Pass:- "+pass);
		
		Connection CON=null;
		PreparedStatement PMT=null;
		
		
			try {
				Driver dbDriver=new Driver();
				DriverManager.registerDriver(dbDriver);
//				Class.forName("com.mysql.cj.jdbc.Driver");
				String dburl="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
				CON=DriverManager.getConnection(dburl);
			
			String querString="insert into new1 values(?,?)";
			PMT=CON.prepareStatement(querString);
			PMT.setString(1, req.getParameter("uname"));
			PMT.setString(2, req.getParameter("pass"));

			int res=PMT.executeUpdate();
			
			if (res!=0) {
				printWriter.println("Successfully Updated");
			}
			
			if (CON!=null) {
				CON.close();
			}
			if (PMT!=null) {
			PMT.close();	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
}
