package asst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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


@WebServlet("/DisplayItems")
public class DisplayItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DisplayItems() {
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

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Entries> dept = new ArrayList<Entries>();
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27?useSSL=false";
	            String username = "cs3220stu27";
	            String password = "*********";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from entries" );

	            while( rs.next() )
	            {
	            Entries entries = new Entries(rs.getInt( "id" ),rs.getString( "type" ),rs.getString("name"),rs.getString("info"),rs.getString("available"));
	                dept.add(entries);
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
	        request.setAttribute("dept", dept);
	        request.getRequestDispatcher("display.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
