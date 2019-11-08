<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h1>Add a new book!</h1>

<form action="./AddBook" method="post" enctype="multipart/form-data" >
    <p>
        <label for="isbn">ISBN:</label>
        <input type="text" name="isbn" id="isbn" required />
    </p>

    <p>
        <label for="title">Title:</label>
        <input type="text" name="title" id="title" required />
    </p>

    <p>
        <label for="author1">Author:</label>
        <input type="text" name="author1" id="author" required />
    </p>

    <p>
        <label for="description">Book Description:</label>
        <input type="text" name="description" id="description" required />
    </p>

    <!-- <p>
        <label for="coverImage">Submit a Cover Image</label>
        <input type="file" name="coverImage" id="coverImage" />
    </p>
    -->
    <br>
    <br>
    <input type="submit" />
</form>
