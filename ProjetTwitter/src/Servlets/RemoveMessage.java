package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class RemoveMessage extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String key = req.getParameter("key");
		String mess_id = req.getParameter("mess_id");
		resp.setContentType( " text / plain " );
		PrintWriter out = resp.getWriter ();
		JSONObject resul = Services.ServiceMessages.removeMessage(key,mess_id);
		out.print(resul.toString());
	}
}
