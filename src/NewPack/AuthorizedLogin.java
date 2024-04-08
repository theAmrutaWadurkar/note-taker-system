package NewPack;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class AuthorizedLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter print=resp.getWriter();
		
		String uname=req.getParameter("uname");
		String pass=req.getParameter("pass");
		
		if (DataClass.dbCheck(uname, pass)) {
			//print.println("Login Successfull");
			HttpSession sn=req.getSession();
			sn.setAttribute("username", uname);
			resp.sendRedirect("NoteTaker.jsp");
			
		} else {
			print.println("Login Un-Successfull Please Check Username & PassWord");
		}
		
		
	}
	
}
