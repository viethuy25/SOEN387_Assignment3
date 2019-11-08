package com.bookstore.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.models.Book;
import com.bookstore.models.User;

/**
 * Servlet implementation class DbTest
 */
public class DbTest extends HttpServlet {
	final private static String db_url = "jdbc:mysql://localhost:3306/bookstore";
	final private static String db_username = "root";
	final private static String db_passwd = "Barcelona2108";
	
	DBConnectionPool connPool = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// User read test
		//User user = new UserDB().selectUser("timgalvin");
		
		// Read all users
		//ArrayList<User> users = new UserDB().selectUsers();
			
		// Book read test
		//Book book = new BookDB().selectBook("S");
				
		//request.setAttribute("user", user);
		//request.setAttribute("users", users);
		//request.setAttribute("book", book);
		//request.getRequestDispatcher("testdb.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
