package database;
import java.io.PrintWriter;  
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletContext;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	       
	      // Set response content type
	      response.setContentType("application/json");
	      response.addHeader("Access-Control-Allow-Origin", "*");
	      PrintWriter out=response.getWriter();
	     	  
	     
	    
	      try {
	         // Register JDBC driver
	    	  
	    	 ServletContext context=getServletContext(); 
	    	 final String DB_URL = context.getInitParameter("dbname");

	    	 //  Database credentials
	    	 final String USER = context.getInitParameter("dbusername");
	    	 final String PASS = context.getInitParameter("dbpassword");
	    	 Class.forName("com.mysql.jdbc.Driver");

	    	 // Open a connection
	    	 Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	 
	         // Execute SQL query
	         Statement stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT id, firstname, lastname FROM customer";
	         ResultSet rs = stmt.executeQuery(sql);
           
	     
	         
	         JSONArray arr = new JSONArray();  
	         // Extract data from result set
	         while(rs.next()){
	            //Retrieve by column name
	        	    int id  = rs.getInt("id");
		            String first = rs.getString("firstname");
		            String last = rs.getString("lastname");
		            
		         //Create a JSON object
	        	 if(rs.isLast())
	        	 {
	        	
	        		 JSONObject obj=new JSONObject();	        	
	        		 obj.put("id",id);    
	        		 obj.put("first",first);    
	        		 obj.put("last",last); 
	        		 arr.add(obj);
	        	 }
	        	 else {
	        		 JSONObject obj=new JSONObject();
	        		 obj.put("id",id);    
	        		 obj.put("first",first);    
	        		 obj.put("last",last); 
	        		 arr.add(obj);
	        	 }
	           
	         }
	         System.out.println(arr);
	         out.print(arr);
	        

	         // Clean-up environment
	         rs.close();
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	  
	         //Handle errors for JDBC
	         se.printStackTrace();
	         
	      } catch(Exception e) {
	    	  
	         e.printStackTrace();
	         
	      } finally {
	        
	      } 
	}

}
