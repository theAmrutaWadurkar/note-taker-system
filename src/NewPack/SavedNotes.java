package NewPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class SavedNotes extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		HttpSession sn=req.getSession( );
		String uname=(String)(sn.getAttribute("username"));
		
		if (uname!=null) {	
		Connection CON=null;
		PreparedStatement PST=null;
		ResultSet RS=null;
		try {
			Driver db=new Driver();
			DriverManager.registerDriver(db);
			
			String dburl="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
			CON=DriverManager.getConnection(dburl);
			
		
			
			String query="select * from notes where username=?";
			PST=CON.prepareStatement(query);
			PST.setString(1, uname);
			out.println("<br><h1>"+"SAVED NOTES"+"</h1>");
			out.println("<h2>"+"Welcome @ "+uname+"</h2>");
			RS=PST.executeQuery();
			
			out.println("<style>"+
					"td,th,table{border:2px solid black;}"
					+"table{background-color:white;}"
					+"body{background-image: url(book4.jpg);  background-size:cover;}"
					+ "th{background-color: #1a8a30; color: white;}"
					+"tr:nth-child(odd){background-color: #dee3e0;}"
					+"h1{color:#c2043a;}"
					+".log{position:absolute; left:600px; top: 110px; height:30px; width:90px; background:#f75a11; color:white; font-size:17px;}"
					+".add{ height:30px; width:90px; background:#3e9c60; color:white; font-size:17px;}"
					+"#cont{font-style: oblique;}"
					+"</style>");
			
			out.println("<form action=logout method=get> <div><input class=log type=submit name=logout value=LogOut></div></form>");
			
			out.println("<form action=NoteTaker.jsp method=get>");
			out.println("<table><tr><th>"+"Title"+"</th><th>"+"Content"
			+"</th><th>"+"Date"+"</th><th>"+"Action1"+"</th><th>"+"Action2"+"</th></tr>");
			
			while (RS.next()) {
				out.println("<tr><td>"+RS.getString(3)+"</td><td id=cont>"+
			RS.getString(4)+"</td><td>"+RS.getString(5)+"</td><td>"+  "<a href=delete?id="+RS.getInt(1)+">"+"Delete"+"</a>"+"</td><td>"+"<a href=update?id="+RS.getInt(1)+">"+"Update"+"</a>"+"</td></tr>");
			}
			out.println("</table>");
			out.println("<br><input class=add type=submit value=AddNote ></form>");
			if (CON!=null) {
				CON.close();
			}
			if (PST!=null) {
				PST.close();
			}
			if (RS!=null) {
				RS.close();
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			resp.sendRedirect("NewLogin.html");
			out.println("Invalid Request");
		}
	}
}
