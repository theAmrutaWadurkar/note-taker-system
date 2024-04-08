package NewPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class Update extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	int noteid=Integer.parseInt(req.getParameter("id"));

	out.println("<style>" 
			+	".sub {	margin-left: 30px;font-family: Times New Roman, Times, serif; font-size: 20px; color: #d8d8eb;	margin-bottom: 2%; background-color: #508c0f; 	width: 150px; height: 35px; border-radius: 15px; border: 2px solid #508c0f;}"
			+ ".for {	margin-left: 25%; padding: 2%;}"
			
			+"#content {font-size: 15px;font-style: oblique;background-color: #dbd17f;border-radius: 15px;padding: 25px;margin-bottom: 2%;color: #626361;}"
			
			+"body {background-color: #60615e;background-image:url(book.jpg);background-size:cover;	}"
			
			+"h1 {	color: #b9f542;display: inline-block;	}"
			+"h3 {	color: #b9f542;	}"
			
			+"a {font-family: Times New Roman, Times, serif;font-size: 18px;text-decoration: none;border-radius: 15px;padding: 8px;background-color: #dbde18;color: #96177f;margin-left: 20px;}"
			
			+"input[name=title] {font-family: fantasy; font-size : 15px;border-radius: 15px;border:2px solid white;padding: 10px;margin-bottom: 2%;width: 250px;font-size: 15px;}"
			
			+".log {font-family: Times New Roman, Times, serif;font-size: 20px;color: #d8d8eb;background-color: #e60202;width : 150px;height : 35px; border-radius: 15px; border : 2px solid #e60202;position: absolute;top: 180px;right: 610px;width: 150px;height: 35px;}"
			
			+".sess{color:white;font-size:25px;position: absolute;top: 65px;right: 610px;width: 150px;height: 35px;}"
			
			+"</style>");
	
	
	out.println("<form action=\"logout\" method=\"get\">\r\n" + 
			"		<div>\r\n" + 
			"			<input class=\"log\" type=\"submit\" name=\"logout\" value=\"LogOut\">\r\n" + 
			"		</div>\r\n" + 
			"	</form>");
	out.println("<form class=\"for\" action=udconnect method=post>");
	out.println("<h1>"+"Update Note"+"</h1>");
	
	Connection CON=null;
	PreparedStatement PMT=null;
	ResultSet RS=null;
	try {
		Driver db=new Driver();
		DriverManager.registerDriver(db);
		
		String dburl="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
		CON=DriverManager.getConnection(dburl);
		
		String query="select title,content from notes where noteid=?";
		PMT=CON.prepareStatement(query);
		HttpSession session=req.getSession();
		out.println("<h2 class=sess>"+"Welcome @ "+session.getAttribute("username")+"</h2>");
		
		PMT.setInt(1, noteid);
		RS=PMT.executeQuery();
		
		if (RS.next()) {
			out.println("<input type=hidden name=id value="+noteid+">"+"<h3>"+"Title"+"</h3>"+"<input type=text name=title value="+RS.getString(1)+">"+
					"<h3>"+"</h3>"+"<textarea id=\"content\" rows=\"20\" cols=\"55\"  name=content>"+RS.getString(2)+"</textarea>"
					);
			
			out.println("<br><br><input class=\"sub\" type=submit value=Save /><a\r\n" + 
					"					href=\"saved\">Click here to view saved notes </a>");
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
