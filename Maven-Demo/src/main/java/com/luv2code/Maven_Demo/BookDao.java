package com.luv2code.Maven_Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	public BookDao(String jdbcURL,String jdbcUsername,String jdbcPassword) throws SQLException
	{
		this.jdbcURL=jdbcURL;
		this.jdbcUsername=jdbcUsername;
		this.jdbcPassword=jdbcPassword;
	
	}
	protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                                        
        }
    }
	
	protected void disconnect() throws SQLException
	{
		if(jdbcConnection!=null || !jdbcConnection.isClosed())
		{
			jdbcConnection.close();
		}
	}
     
	public boolean insertBook(Book book)throws SQLException
	{
		String query="Insert into Book(title,author,price) values(?,?,?)";
		connect();
		PreparedStatement ps=jdbcConnection.prepareStatement(query);
		ps.setString(1,book.getTitle());
		ps.setString(2,book.getAuhor());
		ps.setFloat(3,book.getPrice());
		
		boolean rowinserted=ps.executeUpdate()>0;
		ps.close();
		disconnect();
		return rowinserted;
	}
	
	public List<Book>listAllBooks()throws SQLException
	{
		List<Book> listbook=new ArrayList<Book>();
		String query="Select * from Book";
		connect();
		Statement st=jdbcConnection.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next())
		{
			int id=rs.getInt("id");
			String title=rs.getString("title");
			String author=rs.getString("author");
			float price=rs.getFloat("price");
			
			Book book=new Book(id,title,author,price);
			listbook.add(book);
		}
		st.close();
		rs.close();
		disconnect();
		
		return listbook;
	}
	
	public boolean deleteBook(Book book) throws SQLException
	{
		String query="Delete from Book where id=?";
		connect();
		PreparedStatement ps=jdbcConnection.prepareStatement(query);
		ps.setInt(1,book.getId());
		boolean rowDeleted=ps.executeUpdate()>0;
		ps.close();
		disconnect();
		return rowDeleted;
		
	}
	
	public boolean updateBook(Book book) throws SQLException
	{
		String query="Update Book set title=?,author=?,price=?";
		query+="where id=?";
		connect();
		PreparedStatement ps=jdbcConnection.prepareStatement(query);
		ps.setString(1,book.getTitle());
		ps.setString(2,book.getAuhor());
		ps.setFloat(3,book.getPrice());
		boolean rowUpdated=ps.executeUpdate()>0;
		ps.close();
		disconnect();
		return rowUpdated;
		
	}
	
	public Book getBook(int id)throws SQLException
	{
		Book book=null;
		String query="Select * from Book where id=?";
		PreparedStatement ps=jdbcConnection.prepareStatement(query);
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			String title=rs.getString("title");
			String author=rs.getString("author");
			float price=rs.getFloat("id");
			Book b= new Book(id,title,author,price);
		}
		rs.close();
		ps.close();
		return book;
	}
}
		
