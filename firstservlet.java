

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class firstservlet
 */
@WebServlet("/firstservlet")
public class firstservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public firstservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("In Get method");
		//doPost(request,response);
		response.setContentType("text/html");
		HttpSession s=request.getSession();
		
		PrintWriter out=response.getWriter();
		String display_code ="";
		String uname = request.getParameter("username");
		//String sec_user_name = request.getParameter("user_n");
		String sec_user_name= (String)s.getAttribute("second_uname");
		
		/*Date myDate = new Date();
		SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
		String mdy = mdyFormat.format(myDate);*/
		
		//System.out.println("uname from html "+uname);
		//System.out.println("second user name  "+sec_user_name);
		if(sec_user_name!=(null))
		{
			//System.out.println("Inside outer if");
			if(sec_user_name.length()==0)
				
			{
				//System.out.println("Inside inner if");
				display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+sec_user_name+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea align=\"center\" name=\"post_box\" rows=\"4\" cols=\"50\">"+
						   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
			}
			else
			{
				//System.out.println("Inside inner else");
				display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+sec_user_name+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+sec_user_name+" /><textarea align=\"center\" name=\"post_box\" rows=\"4\" cols=\"50\">"+
						   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
			}
		}
		else
		{
			//System.out.println("Inside outer else");
			display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+sec_user_name+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea align=\"center\" name=\"post_box\" rows=\"4\" cols=\"50\">"+
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
	        Date myDate = new Date();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
			String mdy = mdyFormat.format(myDate);
	        while(rsk.next())
	        {
	           
	        //	System.out.println("Came here 3");
	        	display_code= display_code + "<p align=\"center\"><b><u> "+rsk.getString(1)+"</u></b></p>" + "<p class=\"msg\" align=\"center\"> "+mdy+" &nbsp &nbsp "+rsk.getString(2)+" </p>";
	        	
	        	
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
		 Cookie user_cookie = new Cookie("user_def_cookie", sec_user_name);
	//	cookies.
		
		
		 
		 
        response.addCookie(cookies[0]);
        
     //  System.out.println(cookies[1].getValue());
       
		 response.setIntHeader("Refresh", 10);
		 display_code=display_code + "</form></body></html>";
		 
		// response.addCookie(user_cookie);
		
	        out.println(display_code);
		//HttpSession s=request.getSession();
		//s.setAttribute("second_uname",uname);
		
		/* request.getSession();
		   response.setIntHeader("Refresh", 3);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<h3>Hello Readers</h3>");
		out.println("</body></html>");
		
		Cookie[] cookies = request.getCookies();
	    for (int i = 0; i < cookies.length; i++) {
	        out.println("Name: " + cookies[i].getName() + "; Value: " + cookies[i].getValue());
	    }
	   */
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String display_code ="";
		String uname = request.getParameter("username");
		String sec_user_name = request.getParameter("user_n");
		
		System.out.println("uname from html "+uname);
		System.out.println("second user name  "+sec_user_name);
		if(sec_user_name!=(null))
		{
			//System.out.println("Inside FS outer if");
			if(sec_user_name.length()==0)
			{
				//System.out.println("Inside FS inner if");
				display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+uname+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea align=\"center\" name=\"post_box\" rows=\"4\" cols=\"50\">"+
						   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
			}
			else
			{
				//System.out.println("Inside FS inner else");
				display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+uname+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+sec_user_name+" /><textarea align=\"center\" name=\"post_box\" rows=\"4\" cols=\"50\">"+
						   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
			}
		}
		else
		{
			//System.out.println("Inside FS outer else");
			display_code = "<html><body><link rel=\"stylesheet\" type=\"text/css\" href=\"style_cs.css\"><h1 align=\"center\" style=\" font-family:Apple Chancery, cursive; font-size:300%;text-decoration: underline;color:red\">Welcome "+uname+"<br/></h1><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea align=\"center\" name=\"post_box\" rows=\"4\" cols=\"50\">"+
					   " </textarea><br/><input type=\"submit\" value=\"push\"/>"; 
		}	
			
	//	display_code = "<html><form method=\"post\" action=\"insert_into_DB\">"+"<input name=\"user_n\" type=\"hidden\" value="+uname+" /><textarea name=\"post_box\" rows=\"4\" cols=\"50\">"+
 //  " </textarea><br/><input type=\"submit\" value=\"push\"/>";  
		//Cookie user_cookie = new Cookie("user_name", sec_user_name);
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
	        Date myDate = new Date();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
			String mdy = mdyFormat.format(myDate);
	        while(rsk.next())
	        {
	           
	        //	System.out.println("Came here 3");
	        	display_code= display_code + "<p align=\"center\"><b><u> "+rsk.getString(1)+"</u></b></p>" + "<p class=\"msg\" align=\"center\"> "+mdy+" &nbsp &nbsp "+rsk.getString(2)+" </p>";
	        	
	        	
	        	//stmt.executeQuery(sql);
	        }
	     
	        con.close();
	       // JOptionPane.showMessageDialog(this,"Record Updated Successfully!");  
	       
	       }
		 catch(Exception e)
		 {
			 System.out.println(e.toString());
		 }
		 display_code=display_code + "</form></body></html>";
		 
		
		 
	        out.println(display_code);
	        
		//out.println("<html><body>");
		//out.println("<h3>Hello Readers</h3>");
		//out.println("</body></html>");
		//request.getSession();
		HttpSession s=request.getSession();
		Cookie[] cookies = request.getCookies();
        
	//	String uname = request.getParameter("username");
		//request.setAttribute("user_name", uname);
		//request.setAttribute("msg",)
		//out.println("<h2>Hi "+uname+" </h2>");
		//out.println("</body></html>");
		 response.setIntHeader("Refresh", 10);
		  
		  
		 //response.addCookie(cookies[0]);
			s.setAttribute("second_uname",uname);
		  
		
		
		
		// TODO Auto-generated method stub
	}

}
