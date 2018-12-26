

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class insert_into_DB
 */
@WebServlet("/insert_into_DB")
public class insert_into_DB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert_into_DB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String msg=request.getParameter("post_box"); 
		String user= request.getParameter("user_n");
		System.out.println(msg);
		System.out.println(user);*/
		//doPost(request,response);
		response.setContentType("text/html");
		HttpSession s=request.getSession();
		
	
		PrintWriter out=response.getWriter();
		String display_code ="";
		String uname = request.getParameter("username");
		//String sec_user_name = request.getParameter("user_n");
		String sec_user_name= (String)s.getAttribute("second_uname");
		//System.out.println("uname from html "+uname);
		//System.out.println("second user name  "+sec_user_name);
		if(sec_user_name!=(null))
		{
			//System.out.println("Inside outer if");
			if(sec_user_name.length()==0)
				
			{
				System.out.println("Inside inner if");
				display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+sec_user_name+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea name=\"post_box\" rows=\"4\" cols=\"50\">"+
						   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
			}
			else
			{
				//System.out.println("Inside inner else");
				display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+sec_user_name+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+sec_user_name+" /><textarea name=\"post_box\" rows=\"4\" cols=\"50\">"+
						   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
			}
		}
		else
		{
			//System.out.println("Inside outer else");
			display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+sec_user_name+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea name=\"post_box\" rows=\"4\" cols=\"50\">"+
					   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
		}
			
		try
	       {// test.setText(k);  
			// System.out.println("Came here");
	        Class.forName("oracle.jdbc.driver.OracleDriver");  
	        Connection con=DriverManager.getConnection(  
	"jdbc:oracle:thin:@localhost:1521:xe","ssunkara","susheel");
	        Statement stmt=con.createStatement();  
	        Statement state_result= con.createStatement();
	        ResultSet rsk= stmt.executeQuery("select * from messages_tab");
	      //  System.out.println("Came here 2");
	        while(rsk.next())
	        {
	           
	        //	System.out.println("Came here 3");
	        	display_code= display_code + "<p align=\"center\"><b><u> "+rsk.getString(1)+"</u></b></p>" + "<p class=\"msg\" align=\"center\"> "+rsk.getString(2)+" </p>";
	        	
	        	
	        	//stmt.executeQuery(sql);
	        }
	     
	        con.close();
	       // JOptionPane.showMessageDialog(this,"Record Updated Successfully!");  
	       
	       }
		 catch(Exception e)
		 {
			 System.out.println(e.toString());
		 }
		Cookie[] cookies = request.getCookies();
        response.addCookie(cookies[0]);
		 response.setIntHeader("Refresh", 10);
		 display_code=display_code + "</form></body></html>";
	        out.println(display_code);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String user= (String)request.getAttribute("user_name");
		//response.setContentType("text/html");
		String msg=request.getParameter("post_box"); 
		String user= request.getParameter("user_n");
		//System.out.println(msg);
		//System.out.println(user);
		HttpSession s=request.getSession();
		String second_u= (String)s.getAttribute("second_uname");
		//System.out.println("second_u is "+ second_u);
		
		try
	       {// test.setText(k);  
			// System.out.println("Came here");
	        Class.forName("oracle.jdbc.driver.OracleDriver");  
	        Connection con=DriverManager.getConnection(  
	"jdbc:oracle:thin:@localhost:1521:xe","ssunkara","susheel");
	       // Statement stmt=con.createStatement();  
	        PreparedStatement stmt= con.prepareStatement("insert into messages_tab values(?,?)");
	       stmt.setString(1,second_u);
	       stmt.setString(2, msg);
	       // Statement state_result= con.createStatement();
	        //ResultSet rsk= stmt.executeQuery("select * from messages_tab");
	        stmt.execute();
	       //  stmt.executeUpdate("insert into messages_tab values( '"+second_u+"','"+msg+"' ) ");
	      //  System.out.println("Came here 2");
	       /* while(rsk.next())
	        {
	           
	        	System.out.println("Came here 3");
	        	display_code= display_code + "<p><b><u> "+rsk.getString(1)+"</u></b></p><br/>" + "<p> "+rsk.getString(2)+" </p><br/><br/>";
	        */	
	        	
	        	//stmt.executeQuery(sql);
	       // }
	     
	        con.close();
	       // RequestDispatcher rd = request.getRequestDispatcher("firstservlet");
	        //rd.forward(request, response);
	        
	       // JOptionPane.showMessageDialog(this,"Record Updated Successfully!");  
	       
	       }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
		String display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+second_u+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+second_u+" /><textarea name=\"post_box\" rows=\"4\" cols=\"50\">"+
			
				" </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
		PrintWriter out=response.getWriter();
		
		try
	       {// test.setText(k);  
			// System.out.println("Came here");
	        Class.forName("oracle.jdbc.driver.OracleDriver");  
	        Connection con=DriverManager.getConnection(  
	"jdbc:oracle:thin:@localhost:1521:xe","ssunkara","susheel");
	        Statement stmt=con.createStatement();  
	        Statement state_result= con.createStatement();
	        ResultSet rsk= stmt.executeQuery("select * from messages_tab");
	      //  System.out.println("Came here 2");
	        while(rsk.next())
	        {
	           
	        //	System.out.println("Came here 3");
	        	display_code= display_code + "<p align=\"center\"><b><u> "+rsk.getString(1)+"</u></b></p>" + "<p class=\"msg\" align=\"center\"> "+rsk.getString(2)+" </p>";
	        	
	        	
	        	//stmt.executeQuery(sql);
	        }
	     
	        con.close();
	       // JOptionPane.showMessageDialog(this,"Record Updated Successfully!");  
	       
	       }
		 catch(Exception e)
		 {
			 System.out.println(e.toString());
		 }
		 response.setIntHeader("Refresh", 10);
		// System.out.println(s.getId());
		 display_code=display_code + "</form></body></html>";
	        out.println(display_code);
	        //response.addCookie();
	        Cookie[] cookies = request.getCookies();
	        response.addCookie(cookies[0]);
	        
		
	}

}
