package NewPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class NoteWriter extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
	
		HttpSession sn=req.getSession();
		
		String unameString=(String)(sn.getAttribute("username"));
		if (unameString!=null) {
		System.out.println(unameString);
		//**************************Conversion date to String******************************************
		String pattern = "MM/dd/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);
		Date d1=new Date();
		String date = df.format(d1);
		//*************************************************************************
		Connection CON=null;
		PreparedStatement PMT=null;
		
		try {
			Driver db=new Driver();
			DriverManager.registerDriver(db);
			
			String dburl="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
			CON=DriverManager.getConnection(dburl);
			
			String query="insert into notes(username,title,content,date) values(?,?,?,?)";
			PMT=CON.prepareStatement(query);
			
			PMT.setString(1, unameString);
			PMT.setString(2, req.getParameter("notename"));
			PMT.setString(3, req.getParameter("content"));
			PMT.setString(4, date);
			
			int res=PMT.executeUpdate();
			
			if (res!=0) {
				//out.println("<h1>"+"Successfully Saved"+"</h1>");
				resp.sendRedirect("saved");
			}else {
				out.println("Not Updated");
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
		
	  }else {
		  resp.sendRedirect("NewLogin.html");
	  	}
	}
	
}
