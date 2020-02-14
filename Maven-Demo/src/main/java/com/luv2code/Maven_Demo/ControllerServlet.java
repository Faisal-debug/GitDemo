package com.luv2code.Maven_Demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   private BookDao bookDao;
    public void init()
    {
    	String jdbcURL=getServletContext().getInitParameter("jdbcURL");
    	String jdbcUserName=getServletContext().getInitParameter("jdbcUsername");
    	String jdbcPassword=getServletContext().getInitParameter("jdbcPassword");
    	try {
			bookDao = new BookDao(jdbcURL,jdbcUserName,jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getServletPath();
		try
		{
		switch(action)
		{
		case "/new":
			showNewForm(request,response);
			break;
		case "/insert":
			insertBook(request,response);
			break;
		case "/delete":
			deleteBook(request,response);
			break;
		case "/edit":
			showEditForm(request,response);
			break;
		case "/update":
			updateBook(request,response);
			default:
				listBook(request,response);
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		
				
		}
		
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException,ServletException
	{
		List<Book> listBook=bookDao.listAllBooks();
		request.setAttribute("listBook",listBook);
		request.getRequestDispatcher("BookList.jsp").forward(request, response);	
	}
	
	public void showNewForm(HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException,ServletException
	{
		request.getRequestDispatcher("BookForm.jsp").forward(request, response);
	}
	
	public void showEditForm(HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException,ServletException
	{
		int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDao.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
	}
	
	public void insertBook(HttpServletRequest request,HttpServletResponse response)throws IOException,SQLException,ServletException
	{
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		float price=Float.parseFloat(request.getParameter("price"));
		
		Book newbook=new Book(title,author,price);
		bookDao.insertBook(newbook);
		response.sendRedirect("list");
	}
	
	 private void updateBook(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        float price = Float.parseFloat(request.getParameter("price"));
	 
	        Book book = new Book(id, title, author, price);
	        bookDao.updateBook(book);
	        response.sendRedirect("list");
	    }
	 
	    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	 
	        Book book = new Book(id);
	        bookDao.deleteBook(book);
	        response.sendRedirect("list");
	 
	    }
	
	
	
	

}
