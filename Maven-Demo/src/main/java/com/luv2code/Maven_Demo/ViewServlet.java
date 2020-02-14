package com.luv2code.Maven_Demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='Employee.html'>Add New Employee</a>"); 
		out.print("<h1> Employee List</h1>");
		
		List<Employee>list=EmpDao.getAllEmployees();
	    out.print("<table border='1' width='100%'");  
	   // out.print("<tr><th>Id" "</th><th>Name</th>"<th>Password</th><th>Email</th><th>Country</th> <th>Edit</th><th>Delete</th></tr>\");
               
		
		for(Employee e:list)
		{
			out.print("<tr><td>"  +"</td><td>" +e.getName()+"</td><td>" +e.getPassword()
			+"</td><td>" +e.getEmail() +"</td><td>" +e.getCountry()+"</td><td><a href='EditServlet?name="+e.getName()+"'>edit</a>" 
			
			 +"</td><td><a href='DeleteServlet?name="+e.getName()+"'>delete</a></td></tr>");
	
			
		}
		out.print("</table>");
		
		
	}

	
}
