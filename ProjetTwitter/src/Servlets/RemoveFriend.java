package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class RemoveFriend extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String key = req.getParameter("key");
		String login = req.getParameter("login");
		resp.setContentType( " text / plain " );
		PrintWriter out = resp.getWriter ();
		JSONObject resul = Services.ServiceFriend.removeFriend(key,login);
		out.print(resul.toString());


	}

}
