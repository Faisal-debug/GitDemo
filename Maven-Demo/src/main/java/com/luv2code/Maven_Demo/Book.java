package com.luv2code.Maven_Demo;

public class Book {

	protected int id;
	protected String title;
	protected String auhor;
	protected float price;
	
 public	Book()
	{
		
	}
	
  public Book(int id)
  {
   this.id=id;
   }
 
  public Book(int id,String title,String author,float price)
  {
	  this(title,author,price);
	  this.id=id;
	  
  }
  
  public Book(String title,String author,float price)
  {
	  this.title=title;
	  this.auhor=author;
	  this.price=price;
  }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAuhor() {
	return auhor;
}

public void setAuhor(String auhor) {
	this.auhor = auhor;
}

public float getPrice() {
	return price;
}

public void setPrice(float price) {
	this.price = price;
}
  
  
  
  
  
}
