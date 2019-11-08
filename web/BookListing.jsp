<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import="com.bookstore.db.BookDB, java.text.*" %>
<jsp:useBean id="book" scope="request" class="com.bookstore.models.Book"></jsp:useBean>
<jsp:setProperty name="book" property="*"></jsp:setProperty>

<div class="panel panel-default">
    <div class="panel-heading">
    <h3 class="panel-title">${book.title}</h3>
    </div>
  
    <div class="panel-body">
        <img src="./book_images/${book.coverImageFile}" alt="${book.title} cover" width="200">
        <form action="./cartUpdate" method="post">
            <h4>ISBN: ${book.isbn}</h4>
            <h4>Author: ${book.author}</h4>
            <h4>Description:</h4>
            <p>${book.description}</p>
            
            <input type="hidden" name="isbn" value="${book.isbn}" />
            <input type="hidden" name="title" value="${book.title}" />
        </form>
      </div>
</div>

<div class="content">
</div>
        
</body>
<!-- <p><a href="./FirstTest">Test Servlet functionality</a></p> -->
