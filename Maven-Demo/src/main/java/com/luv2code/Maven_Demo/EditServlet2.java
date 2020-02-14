package com.luv2code.Maven_Demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet2
 */
@SuppressWarnings("serial")
@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		
		Employee e= new Employee();
		e.setName(name);
		e.setPassword(password);
		e.setEmail(email);
		e.setCountry(country);
		
		int status=EmpDao.update(e);
		
		if(status>0)
		{
			out.print("Record Updated Successfullt");
			response.sendRedirect("ViewServlet");
		}
		else
		{
			out.print("Sorry....! Record Not Updated");
		}
		out.close();
		
	}

}
