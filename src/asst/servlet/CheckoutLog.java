package asst.servlet;

import java.io.IOException;
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

import assignment.model.CO;
import assignment.model.Entries;



@WebServlet("/CheckoutLog")
public class CheckoutLog extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckoutLog() {
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
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		List<CO> list = new ArrayList<CO>();
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu27?useSSL=false";
	            String username = "cs3220stu27";
	            String password = "VXlx!72S";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from checkout where id = "+id+" order by returned " );

	            while( rs.next() )
	            {
	            CO checkout = new CO(rs.getInt("id"), rs.getInt( "cin" ),rs.getString( "name" ),rs.getDate("borrowed"),rs.getDate("due"),rs.getDate("returned"));
	                list.add(checkout);
	            }
	            
	            ResultSet rs1 = stmt.executeQuery( "select * from entries where id = "+id+" " );

	            while( rs1.next() )
	            {
	            Entries item = new Entries(rs1.getInt( "id" ),rs1.getString( "type" ),rs1.getString("name"),rs1.getString("info"),rs1.getString("available"));
	            request.setAttribute("item", item);
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
	        
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
