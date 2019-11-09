<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
    Object user_check = session.getAttribute("user");
    String user_check_LoggedIn = "";
    if (user_check != null) 
        user_check_LoggedIn = (String) user_check;
    if(user_check_LoggedIn.length()==0){
        session.setAttribute("checkErr", "Please Login");
        
        // REMOVE THIS LATER
        //response.sendRedirect("./SignUp.jsp");
    }
 %>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- boostrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./">SOEN 387 Assignment 2</a>
        </div>
          
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-left" action="./Search" method="get">
                <input type="text" class="form-control" name="search-query" id="search-query" size="40" placeholder="ISBN, Title, Author, Keyword...">
            </form>
            
            <ul class="nav navbar-nav navbar-right">
                <% 
                    Object userIn = session.getAttribute("user");
                    String userLoggedIn = "";
                    if (userIn != null) {
                        userLoggedIn = (String) userIn;
                %>
                        <li>
                        <a href="#"><%=userLoggedIn %></a>
                        </li>
                        <li><a href="./logout.jsp">Logout</a></li>                           
                <%
                    } else {
                    // Not logged in, show login prompt
                %>
                    <li><a href="./SignUp.jsp">Sign in/Register</a></li> 
                    <% } %>
                
                <li><a href="./AddBook.jsp">Add/Update Book</a></li>
                <li><a href="./DelBook.jsp">Delete Book</a></li>
                <li><a href="./Search?search-query=*">View All Books</a></li>
                <c:forEach var="row" items="${result.rows}">
                <li><a href="./Search?search-query=${row.genre_name}">${row.genre_name}</a></li>
                </c:forEach>
                
            </ul>
        </div>
      </div>
</nav>

<div style="padding: 70px 15px; text-align: center;">
<div class="container">
    
