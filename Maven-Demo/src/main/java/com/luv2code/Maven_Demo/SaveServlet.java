package com.luv2code.Maven_Demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveServlet
 */
@SuppressWarnings("serial")
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//int id=Integer.parseInt(request.getParameter("Id"));
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		Employee e=new Employee();
		e.setName(name);
		e.setPassword(password);
		e.setEmail(email);
		e.setCountry(country);
		
		int status=EmpDao.save(e);
		
		if(status==1)
		{
			out.print( "Record Entered Successfully");
			request.getRequestDispatcher("Employee.html").include(request, response);
		}
		else
		{
			out.print("Sorry!!!unable to Save Record");
		}
		out.close();
		
		
		}

}
