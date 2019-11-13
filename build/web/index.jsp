<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.FileReader"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@ page import="com.bookstore.db.BookDB, com.bookstore.models.Book, java.util.*, java.text.NumberFormat" %>
<head>
	<title>SOEN 387- A2</title>
</head>


<h1>SOEN 387 Assignment 2</h1>

<%
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    BookDB bk = new BookDB();
    ArrayList<Book> books = bk.selectAllBooks();
	
    Object obj = new JSONParser().parse(new FileReader("C:\\Users\\vieth\\Desktop\\SOEN 387\\Assignment\\A2\\SOEN387_Assignment2\\user.json"));
    out.println (obj);
        
%>
</body>