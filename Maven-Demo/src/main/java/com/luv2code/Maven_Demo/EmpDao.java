package com.luv2code.Maven_Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

	public static Connection getConnection()
	{
		String url="jdbc:mysql://localhost:3306/employee";
		String user="root";
		String passw="Jktech@123";
		
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,passw);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
		
	}
	
	public static int save(Employee e)
	{
		int status=0;
		try
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into employeedetails (name,password,email,country) values(?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			//System.out.println(e.getName()+"\t"+e.getPassword());
			status=ps.executeUpdate();
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return status;
	}
	
	public static List<Employee>getAllEmployees()
	{
		List<Employee>list=new ArrayList<Employee>();
		
		try
		{
			Connection con=EmpDao.getConnection();
		    PreparedStatement pst=con.prepareStatement("Select * from employeedetails");
		    ResultSet rs=pst.executeQuery();
		    while(rs.next())
		    {
		    	Employee e=new Employee();
		    	
		    	e.setName(rs.getString(1));
		    	e.setPassword(rs.getString(2));
		    	e.setEmail(rs.getString(3));
		    	e.setCountry(rs.getString(4));
		    	list.add(e);
		    }
		    con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean delete(String name)
	{
		boolean status=false;
		try
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from employeedetails where name=?");
			ps.setString(1,name);
			status=ps.executeUpdate()>0;
			con.close();
		}
		catch(Exception exe)
		{
			exe.printStackTrace();
		}
		return status;
	}
	
	public static int update(Employee e)
	{
		int status=0;
		try
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update employeedetails set password=?,email=?,country=? where name=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			
			status=ps.executeUpdate();
			
		}
		catch(Exception exe)
		{
			exe.printStackTrace();
		}
		return status;
	}
	
	public static Employee getEmployeebyName(String name)
	{
		Employee e=new Employee();
		try
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("Select * from employeedetails where name=?");
			ps.setString(1,name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				e.setName(rs.getString("name"));
				e.setPassword(rs.getString("password"));
				e.setEmail(rs.getString("email"));
				e.setCountry(rs.getString("country"));
			}
			con.close();
		
	}catch(Exception ex)
	{ 
		ex.printStackTrace();
	}
		return e;
	}
}
