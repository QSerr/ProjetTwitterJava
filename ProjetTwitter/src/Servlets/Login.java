package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import bd.UserBD;

public class Login extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		resp.setContentType( " text / plain " );
		PrintWriter out = resp.getWriter ();
		JSONObject resul = Services.ServiceAccount.Login(login, password);
		out.print(resul.toString());
	}
}
