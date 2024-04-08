package NewPack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServHtml extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter printWriter=resp.getWriter();
		printWriter.println("<style>"+
				"h1{ color:red;} .o{border: 2px solid red;} #btn{"
				+ "background-color:green; color:yellow;} "
				
		
				
				+"</style>" );
		printWriter.println(
				"<h1>" +"Login Here"+ "</h1> <form action=two method=get>"+ " UserName : "+"<input class=o type=text name=unam placeholder=EnterName /><br><br>"
			+"Pass : "+"<input class=o type=password name=pass placeholder=EnterPassword><br><br><input id=btn type=submit value=Login>"
				
				);
		
	}
	
}
