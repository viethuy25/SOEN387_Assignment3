<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="books" scope="session" class="java.util.ArrayList"></jsp:useBean>
<div class="container">
    <table class="table">
        <thead>
            <th>View</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
        </thead>

        <tbody>
            <c:forEach items="${books}" var="book">
                <tr style="text-align: left">
                <td>
                    <form action="./BookListing.jsp" method="post">
                        <input type="hidden" name="isbn" id="isbn" value="${book.isbn}" />
                        <input type="hidden" name="title" id="title" value="${book.title}" />
                        <input type="hidden" name="description" id="description" value="${book.description}" />
                        <input type="hidden" name="author" id="author" value="${book.author}" />
                        <input type="hidden" name="coverImageFile" id="coverImageFile" value="${book.coverImageFile}" />
                        <input type="submit" value="View" />
                    </form>
                </td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.isbn}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>