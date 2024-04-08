package NewPack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sn=req.getSession(false);
		if (sn!=null) {
			sn.invalidate();
			resp.sendRedirect("NewLogin.html");
		}else {
			resp.sendRedirect("NewLogin.html");	
		}
	}
	
}
