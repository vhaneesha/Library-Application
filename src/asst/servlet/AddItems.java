package asst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.model.Entries;


@WebServlet("/AddItems")
public class AddItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AddItems() {
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
		
		 if(request.getSession().getAttribute("user") == null) {
	        	response.sendRedirect("Login");
	            return;
	        }
		 else {
			 ArrayList<String> types = new ArrayList<String>();
			 Connection c = null;
		        try
		        {
		            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27?useSSL=false";
		            String username = "cs3220stu27";
		            String password = "VXlx!72S";

		            c = DriverManager.getConnection( url, username, password );
		            Statement stmt = c.createStatement();
		            ResultSet rs = stmt.executeQuery( "select * from types" );

		            while( rs.next() )
		            {
		            		types.add(rs.getString("name"));
		            }

		           
		            c.close();
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
		        request.setAttribute("types", types);
		       

			 request.getRequestDispatcher("add.jsp").forward(request, response);
		
		 }
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				
			int copies = Integer.parseInt(request.getParameter("copies"));
			
			 Connection c = null;
		        try
		        {
		            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27";
		            String username = "cs3220stu27";
		            String password = "VXlx!72S";

		            c = DriverManager.getConnection( url, username, password );
		            while(copies>0) {
						
						
					
		            String sql = "insert into entries(type, name, info, available) values(?,?,?,?); ";

		            PreparedStatement pstmt = c.prepareStatement( sql );
		            pstmt.setString(1, request.getParameter("type"));
		            pstmt.setString(2, request.getParameter("name"));
		            pstmt.setString(3,request.getParameter("info") );
		            pstmt.setString(4,"Yes" );
		            
		            pstmt.executeUpdate();
		            copies--;
		            }

		           
		            c.close();
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
		 
			
			response.sendRedirect("DisplayItems");
	}

}
