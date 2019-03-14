package asst.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.model.CO;
import assignment.model.Entries;

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckOut() {
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

		if(request.getSession().getAttribute("user") == null || request.getParameter( "id" ) == null) {
        	response.sendRedirect("Login");
            return;
        }
		else {
		Integer id =Integer.valueOf(request.getParameter("id"));
		
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27";
	            String username = "cs3220stu27";
	            String password = "**********";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select curdate();" );

	            while( rs.next() )
	            {
	            		Date currentdate = rs.getDate("curdate()");
	            		 request.setAttribute("cd", currentdate);
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
	       
	        request.setAttribute("id", id);
	        request.setAttribute("name", request.getParameter("name"));
		 request.getRequestDispatcher("editco.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
      
		List<CO> list = new ArrayList<CO>();
		Connection c = null;
        try
        {
        
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27";
            String username = "cs3220stu27";
            String password = "**********";

            c = DriverManager.getConnection( url, username, password );
            String sql = "insert into checkout(id,cin,name,borrowed,due) values(?,?,?,?,?)";
          	
	           PreparedStatement pstmt = c.prepareStatement( sql );
            		pstmt.setInt(1,Integer.valueOf(request.getParameter( "id" )));
	           pstmt.setInt(2, Integer.parseInt(request.getParameter("cin")));
	           pstmt.setString(3, request.getParameter("Name"));
	           pstmt.setDate(4,java.sql.Date.valueOf(request.getParameter("borrowed")));
	           if(request.getParameter("due").equals("null") || request.getParameter("due").trim().length() == 0) {
       				Date due = null;
       				pstmt.setDate(5,(java.sql.Date) due);
       			}
       			else {
       			pstmt.setDate(5,java.sql.Date.valueOf(request.getParameter("due")));
       			}
	            
	            pstmt.executeUpdate();
	            
	          String sql1 = "update entries set available = 'No' where id = ? ";
	          	
		           PreparedStatement pstmt1 = c.prepareStatement( sql1 );
		           pstmt1.setInt(1,Integer.valueOf(request.getParameter( "id" )));
		           pstmt1.executeUpdate();
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
        
        Integer id = Integer.valueOf(request.getParameter("id"));
        response.sendRedirect("CheckoutLog?id="+id+"");
	}

}
