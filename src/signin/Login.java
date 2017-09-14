package signin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
	    response.addHeader("Access-Control-Allow-Origin", "*");
	      
		String username = request.getParameter("username");
		String password = request.getParameter("password");

	
			if(username.equals("aditya@gmail.com")  && password.equals("asdf")) {
				
		     System.out.println("new session set");
		     HttpSession session = request.getSession(true);
			 session.setAttribute("username", username); 
			 out.print("{ "+"\"status\":"+"\"true\""+ "}");
			 
			}
		   else {			
			out.print("{ "+"\"status\":"+"\"false\""+ "}");
		}
	}
}
