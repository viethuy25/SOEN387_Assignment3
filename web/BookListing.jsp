<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import="com.bookstore.DAO.Book_Table_Data_Gateway, java.text.*" %>
<jsp:useBean id="book" scope="request" class="com.bookstore.models.Book"></jsp:useBean>
<jsp:setProperty name="book" property="*"></jsp:setProperty>

<div class="panel panel-default">
    <div class="panel-heading">
    <h3 class="panel-title">Tittle: ${book.title}</h3>
    </div>
  
    <div class="panel-body">
        <h4>Cover image: <img src="./book_images/${book.coverImageFile}" alt="${book.coverImageFile}" width="200"></h4>
        <h4>ISBN: ${book.isbn}</h4>
        <h4>Author: ${book.author}</h4>
        <h4>Description:</h4>
        <p>${book.description}</p>
            
      </div>
</div>

<div class="content">
</div>
        
</body>
<!-- <p><a href="./FirstTest">Test Servlet functionality</a></p> -->
