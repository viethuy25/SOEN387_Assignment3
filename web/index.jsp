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

<%@ page import="com.bookstore.DAO.Book_Table_Data_Gateway, com.bookstore.models.Book, java.util.*, java.text.NumberFormat" %>
<head>
	<title>SOEN 387- A2</title>
</head>


<h1>SOEN 387 Assignment 2</h1>

<%
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    Book_Table_Data_Gateway bk = new Book_Table_Data_Gateway();
    ArrayList<Book> books = bk.selectAllBooks();
	
    Object obj = new JSONParser().parse(new FileReader("C:\\Users\\vieth\\Desktop\\SOEN 387\\Assignment\\A3\\SOEN387_Assignment3\\user.json"));
    out.println (obj);
        
%>

<form class="navbar-form navbar-center" action="./Search?search-query=" method="get">
    <input type="text" class="form-control" name="search-query" id="search-query" size="40" placeholder="ID">
</form>

</body>