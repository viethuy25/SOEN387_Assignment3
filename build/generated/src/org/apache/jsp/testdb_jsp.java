package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileReader;
import com.bookstore.db.BookDB;
import com.bookstore.models.Book;
import com.bookstore.db.DBConnectionPool;
import java.sql.*;

public final class testdb_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>JDBC Connection example</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<h1>JDBC Connection example</h1>\n");
      out.write("\n");

  try {
    Class.forName("com.mysql.jdbc.Driver");
    DBConnectionPool connPool = new DBConnectionPool("jdbc:mysql://localhost:3306/bookstore?autoReconnect=true&useSSL=true&serverTimezone=PDT", "root", "Barcelona2108");
    
    out.println ("database successfully opened.");
    
    Statement stmt = null;
                
    ResultSet rs = null;
    Book book = new Book();
    Connection conn = null;
    conn = connPool.getConnection();

    if(conn != null) {
            stmt = conn.createStatement();

            String strQuery = "select isbn, title, description, cover_image "  
                            + "from books where isbn='S'";
            rs = stmt.executeQuery(strQuery);
            if (rs.next()) {
                    book.setIsbn(rs.getString(1));
                    book.setTitle(rs.getString(2));
                    book.setDescription(rs.getString(3));
                    book.setCoverImageFile(rs.getString(4));
            }
    }
  }
  catch(SQLException e) {
    out.println("SQLException caught: " +e.getMessage());
    out.println("SQLState: " + e.getSQLState());
    out.println("Error: " + e.getErrorCode());
    out.println("StackTrace: " + e.getStackTrace());
  }

      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
