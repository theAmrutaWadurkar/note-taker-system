package NewPack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Serv1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Its One Only1");
		
//		RequestDispatcher requestDispatcher=req.getRequestDispatcher("two");
//		requestDispatcher.include(req, resp);
//		requestDispatcher.forward(req, resp);
		resp.sendRedirect("two");
	}
}