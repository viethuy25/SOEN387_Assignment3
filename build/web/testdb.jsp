
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="com.bookstore.db.BookDB"%>
<%@page import="com.bookstore.models.Book"%>
<%@page import="com.bookstore.db.DBConnectionPool"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>JDBC Connection example</title>
</head>

<body>
<h1>JDBC Connection example</h1>

<%
  try {
    Class.forName("com.mysql.jdbc.Driver");
    DBConnectionPool connPool = new DBConnectionPool("jdbc:mysql://localhost:3306/bookstore", "root", "Barcelona2108");
      
    Statement stmt = null;
                
    ResultSet rs = null;
    Book book = new Book();
    Connection conn = null;
    conn = connPool.getConnection();

    if(conn != null) {
            stmt = conn.createStatement();
            
            String strQuery = "select isbn, title, description "  
                            + "from books where isbn='S'";
            rs = stmt.executeQuery(strQuery);
            if (rs.next()) {
                    book.setIsbn(rs.getString(1));
                    book.setTitle(rs.getString(2));
                    book.setDescription(rs.getString(3));
            }
    }
    out.println ("database successfully opened.");
  }
  catch(SQLException e) {
    out.println("SQLException caught: " +e.getMessage());
    out.println("SQLState: " + e.getSQLState());
    out.println("Error: " + e.getErrorCode());
    out.println("StackTrace: " + e.getStackTrace());
  }
%>
</body>
</html>