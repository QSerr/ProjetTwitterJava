import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.Operations;

public class Addition extends HttpServlet{
	
	public Addition() {}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String a =request.getParameter("a");
		String b =request.getParameter("b");
		String op=request.getParameter("op");
				
		response.setContentType( " text / plain " );
		PrintWriter out = response.getWriter ();
		
		out.println(Operations.calcul(Integer.parseInt(a),Integer.parseInt(b),op));
	}
}
