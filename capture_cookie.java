

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class capture_cookie
 */
@WebServlet("/capture_cookie")
public class capture_cookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public capture_cookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Came here reyyyy");
		String cooke= request.getParameter("ck");
		
		System.out.println("Cookies is :"+ cooke);
		Date myDate = new Date();
		SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy");
		String mdy = mdyFormat.format(myDate);
		System.out.println("Date is "+mdy);
		try
	       {// test.setText(k);  
			// System.out.println("Came here");
	        Class.forName("oracle.jdbc.driver.OracleDriver");  
	        Connection con=DriverManager.getConnection(  
	"jdbc:oracle:thin:@localhost:1521:xe","ssunkara","susheel");
	       // Statement stmt=con.createStatement();  
	        PreparedStatement stmt= con.prepareStatement("insert into cookie_info values(?,?)");
	      stmt.setString(1 ,mdy );
	      
	        stmt.setString(2,cooke);
	      // stmt.setString(2, msg);
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
		
		
		//Cookie[] cookies = request.getCookies();
	    //for (int i = 0; i < cookies.length; i++) {
	     //   System.out.println("Name: " + cookies[i].getName() + "; Value: " + cookies[i].getValue());
	   // }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String cooke= request.getParameter("cookie");
		//System.out.println("Cookies is :"+ cooke);
	}

}
