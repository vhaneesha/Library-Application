package asst.servlet;

import java.io.IOException;
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

@WebServlet("/Type")
public class Type extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Type() {
        super();
        // TODO Auto-generated constructor stub
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
		
	
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27?useSSL=false";
            String username = "cs3220stu27";
            String password = "VXlx!72S";
          
        		
            c = DriverManager.getConnection( url, username, password );
          
            String sql = "update entries set entries.type = ? where entries.type = ? ";
          	
		           PreparedStatement pstmt = c.prepareStatement( sql );
		           pstmt.setString(1, request.getParameter("type"));
		           pstmt.setString(2, request.getParameter("oldtype"));
		  
		            pstmt.executeUpdate();
		           
		            String sql1 = "update types set types.name = ? where types.name = ? ";
		          	
			           PreparedStatement pstmt1 = c.prepareStatement( sql1 );
			           pstmt1.setString(1, request.getParameter("type"));
			           pstmt1.setString(2, request.getParameter("oldtype"));
			           pstmt1.executeUpdate();
			     
		          c.close();
		          //response.sendRedirect("DisplayItems");
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
	
			
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}


