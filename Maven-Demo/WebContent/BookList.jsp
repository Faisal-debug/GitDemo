<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Store Application</title>
</head>
<body>

  <Center>
    <h1> Book Management</h1>
    <h2>
    <a href="/new">Add New Book</a>
      &nbsp;&nbsp;&nbsp;
    <a href="/list">List All Books</a>
    </h2>
    </center>
    
    <div align="center">
    <table border="1" cellpadding="5">
    <caption><h2>List of Books</h2></caption>
    <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Author</th>
    <th>Price</th>
    <th>Action</th>
    </tr>
    
    <c:forEach var="book" items="${listbook}">
    <tr>
    <td><c:out value="${book.id}"/></td>
    <td><c:out value="${book.title}"/></td>
    <td><c:out value="${book.author}"/></td>
    <td><c:out value="${book.price}"/></td>
    
     <td>
                        <a href="/edit?id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${book.id}' />">Delete</a>                     
                    </td>
                    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>