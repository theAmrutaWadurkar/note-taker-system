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

public class UDConnect extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	int id=Integer.parseInt(req.getParameter("id"));
	
	Connection CON=null;
	PreparedStatement PMT=null;
	try {
		Driver db=new Driver();
		DriverManager.registerDriver(db);
		
		String dburl="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
		CON=DriverManager.getConnection(dburl);
		
		String query="update notes set title=?,content=? where noteid=?";
		PMT=CON.prepareStatement(query);
		PMT.setString(1, req.getParameter("title"));
		PMT.setString(2, req.getParameter("content"));
		PMT.setInt(3, id);
		int res=PMT.executeUpdate();
		
		if (res!=0) {
			resp.sendRedirect("saved");;
		}else {
			out.println("Not Updated");
		}
		
		if (CON!=null) {
			CON.close();
		}
		if (PMT!=null) {
			PMT.close();
		}
		
		}
	catch (Exception e) {
		
	}
}
}
