package com.mmh.pkg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/exampleDS")
	private	DataSource ds1;
       
 
	 
    public Login() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		String conUser = "DTU07";
		String conPassword = "FAGP2016";
		try{
			con=ds1.getConnection(conUser,conPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
	
		Controller control = new Controller();
		int result = control.Login(login,password,con);
		
		ServletContext sc = this.getServletContext();
		request.setAttribute("user", login);
		RequestDispatcher rd = sc.getRequestDispatcher("/saveSession.jsp");
			
		if(result == 1){
			request.setAttribute("failedLogin", "false");
		 } else{
		 	request.setAttribute("failedLogin", "true");
		 	rd = sc.getRequestDispatcher("/login.jsp");
		 }
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
