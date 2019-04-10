package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import Services.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUser extends HttpServlet {
	
	public CreateUser() {}
	//localhost:8080/Castanet_Serreau/Servlet/CreateUser?login=JC&password=BestActor1&nom=Vand&prenom=JCJC&sexe=male&age=50
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String sexe = req.getParameter("sexe");
		String age = req.getParameter("age");
		
		resp.setContentType( " text / plain " );
		PrintWriter out = resp.getWriter ();
		out.println("CreateUSer");
		JSONObject resul = Services.ServiceAccount.CreateUser(prenom, nom, login, password, sexe, Integer.parseInt(age));
		try {
			out.println(resul.getBoolean("OK"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
