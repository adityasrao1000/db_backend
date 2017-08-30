package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  ServletContext context=getServletContext(); 
   	      final String DB_URL = context.getInitParameter("dbname");

   	      //  Database credentials
   	      final String USER = context.getInitParameter("dbusername");
   	      final String PASS = context.getInitParameter("dbpassword");
	       
	      // Set response content type
	      response.setContentType("application/json");
	      response.addHeader("Access-Control-Allow-Origin", "*");
	      PrintWriter out=response.getWriter();
	    
	  
	      
	    
	      try {
	         // Register JDBC driver
	         Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             String id = request.getParameter("id");
             System.out.println(id);
             String first = request.getParameter("first");
             String last = request.getParameter("last");
             int userid = Integer.parseInt(id);
	         // Execute SQL query
	         PreparedStatement stmt=conn.prepareStatement("UPDATE customer SET firstname=?, lastname=? WHERE id=?");  	      	         
	         
	         stmt.setString(1,first);
	         stmt.setString(2,last);
	         stmt.setInt(3,userid);
	         
	         int i=stmt.executeUpdate();
	         
	         if(i<=0) {	     	        	 
	        	 response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	        	 out.print("{ \"state\" : \"failed\" }");
	         }else 
	         {	 response.setStatus(HttpServletResponse.SC_OK);      	 
	             System.out.println(i+" records affected"); 
	             out.print("{ \"state\" : \"ok\" }");
	         }
	         	      	        

	         // Clean-up environment
	         
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	    	  out.print("{ \"state\" : \"failed\" }");
	         //Handle errors for JDBC
	         se.printStackTrace();
	         
	      } catch(Exception e) {
	    	  
	         e.printStackTrace();
	         
	      } finally {
	        
	      } 
	}

}
