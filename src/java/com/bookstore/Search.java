package com.bookstore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.DAO.Book_Table_Data_Gateway;
import com.bookstore.models.Book;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("search-query");
		System.out.println("In Search  Servlet: " + query);
                ArrayList<Book> books = null;
                
                if (query.equals(""))
                    books = new Book_Table_Data_Gateway().selectAllBooks();
                else
                    books = new Book_Table_Data_Gateway().searchForBooks(query);
		
                request.setAttribute("books", books);
		request.getRequestDispatcher("Search.jsp").forward(request, response);
	}

}
