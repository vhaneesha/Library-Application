package asst.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Return")
public class Return extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Return() {
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
		if(request.getSession().getAttribute("user") == null || request.getParameter( "id" ) == null) {
        	response.sendRedirect("Login");
            return;
        }
		else {
		//Integer id =Integer.valueOf(request.getParameter("id"));
		
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27?useSSL=false";
	            String username = "cs3220stu27";
	            String password = "VXlx!72S";

	            c = DriverManager.getConnection( url, username, password );
	            String sql1 = "update checkout set returned=curdate() where cin= ?  ";
	          	
		           PreparedStatement pstmt1 = c.prepareStatement( sql1 );
		           pstmt1.setInt(1,Integer.parseInt(request.getParameter( "cin" )));
		           pstmt1.executeUpdate();
		           
		           String sql = "update entries set available = 'Yes' where id = ? ";
		          	
		           PreparedStatement pstmt = c.prepareStatement( sql );
		           pstmt.setInt(1,Integer.valueOf(request.getParameter( "id" )));
		           pstmt.executeUpdate();
	           
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
	        
	        
	       // response.sendRedirect("CheckoutLog?id="+id+"");
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
