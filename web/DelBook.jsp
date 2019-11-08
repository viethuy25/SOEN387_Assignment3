<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h1>Add a new book!</h1>

<form action="./DelBook" method="post" enctype="multipart/form-data" >
    <p>
        <label for="isbn">ISBN:</label>
        <input type="text" name="isbn" id="isbn" required />
    </p>
    
    <br>
    <input type="submit" />
</form>
