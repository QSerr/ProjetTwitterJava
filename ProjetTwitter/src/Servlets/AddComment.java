package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class AddComment extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String key = req.getParameter("key");
		String message_id = req.getParameter("message_id");
		String text = req.getParameter("text");
		resp.setContentType( " text / plain " );
		PrintWriter out = resp.getWriter ();
		JSONObject resul = Services.ServiceMessages.addComment(key, message_id, text);
		out.print(resul.toString());

	}
}