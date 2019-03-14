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


@WebServlet("/EditItems")
public class EditItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EditItems() {
        super();
      
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
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
		
		
		if(request.getSession().getAttribute("user") == null || request.getParameter( "id" ) == null) {
	        	response.sendRedirect("Login");
	            return;
	        }
		else {
			ArrayList<String> types = new ArrayList<String>();
			Connection c = null;
	        try
	        {
	        		Entries entries;
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27";
	            String username = "cs3220stu27";
	            String password = "**********";
	            
	        		Integer id = Integer.valueOf(request.getParameter( "id" ));
	        		
	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from entries where entries.id = "+id+" ");

	            while( rs.next() )
	            {
	            entries = new Entries(rs.getInt( "id" ),rs.getString( "type" ),rs.getString("name"),rs.getString("info"),rs.getString("available"));
	            request.setAttribute("entries", entries);
	               
	            }
	            
	            Statement stmt1 = c.createStatement();
	            ResultSet rs1 = stmt1.executeQuery( "select * from types" );

	            while( rs1.next() )
	            {
	            		types.add(rs1.getString("name"));
	            		
	            }

	            request.setAttribute("types", types);
    				request.getRequestDispatcher("edit.jsp").forward(request, response);
	
		
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
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27";
            String username = "cs3220stu27";
            String password = "VXlx!72S";
            
        		//Integer id = Integer.valueOf(request.getParameter( "id" ));
        		
            c = DriverManager.getConnection( url, username, password );
          
            String sql = "update entries set entries.type = ? ,entries.name = ?, entries.info = ? where entries.id = ? ";
          	
		           PreparedStatement pstmt = c.prepareStatement( sql );
		           pstmt.setString(1, request.getParameter("type"));
		           pstmt.setString(2, request.getParameter("name"));
		           pstmt.setString(3,request.getParameter("info") );
		           pstmt.setInt(4,Integer.valueOf(request.getParameter( "id" )));
		            
		            pstmt.executeUpdate();
		          
	  c.close();
	  response.sendRedirect("DisplayItems");
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
