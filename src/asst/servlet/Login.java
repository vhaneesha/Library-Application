package asst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.model.Entries;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Login() {
        super();
     
    }
    public void init(ServletConfig config) throws ServletException {
		super.init( config );
		 try
	        {
	            Class.forName( "com.mysql.jdbc.Driver" );
	        }
	        catch( ClassNotFoundException e )
	        {
	            throw new ServletException( e );
	        }
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("username") == null && request.getParameter("password") == null) {
			response.sendRedirect("Login");
	
		}
		else {
			
		String user = request.getParameter("username");
		String pd = request.getParameter("password");
		 Connection c = null;
	        try
	        {
			
	             //String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27";
	            // String username = "cs3220stu27";
	           //String password = "***********";
	     

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select count(*) as count from administrators where user = '"+user+"' and password = '"+pd+"'" );

	            while( rs.next() )
	            {
	            		int count = rs.getInt("count");
	             
	            		if(count == 0) {
	            			response.sendRedirect("Login"); 
	            			return;
	            		}
	            		else {
	            			request.getSession().setAttribute("user", user);
	            			response.sendRedirect("DisplayItems");
	            			return;
	            		}
	            }
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
	        
		
	}
	}
}
