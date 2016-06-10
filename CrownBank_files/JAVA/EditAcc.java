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


@WebServlet("/EditAcc")
public class EditAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/exampleDS")
	private	DataSource ds1;
       

    public EditAcc() {
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
		String userID = (String) request.getSession().getAttribute("userID");
		System.out.println(userID);
		Controller control = new Controller();
		userData user = null;
		try {
			user = control.getUserInfo(userID,con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String password = user.getPassword();
		if(request.getParameter(password)!=null){
			password = request.getParameter(password);
		}
		int tel = user.getTelephoneNumber();
		if(request.getParameter("tel")!=null){
			tel = Integer.parseInt(request.getParameter("tel"));
		}
		int post = user.getPostnumber();
		if(request.getParameter("post")!=null){
			post = Integer.parseInt(request.getParameter("post"));
		}
		
		user.setCurrency(request.getParameter("currency"));
		user.setPassword(password);
		user.setTelephoneNumber(tel);
		user.setPostnumber(post);
		
		System.out.println("Data to be sent to DB2: ");
		System.out.println(user.getCurrency());
		System.out.println(user.getPassword());
		System.out.println(user.getCurrency());
		System.out.println(user.getPostnumber());
		System.out.println(user.getTelephoneNumber());
		
		try {
			control.editUserAccount(user, con);
			request.setAttribute("success", "true");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("success", "false");
		}
		
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/editAcc.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
