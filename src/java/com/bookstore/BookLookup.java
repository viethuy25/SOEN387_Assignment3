package com.bookstore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.DAO.Book_Table_Data_Gateway;
import com.bookstore.models.Book;

/**
 * Servlet implementation class Search
 */
public class BookLookup extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private static Book book = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookLookup() {
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
		String isbn = (String) request.getParameter("isbn");
		System.out.println("Retreived ISBN: " + isbn);
                
                if (book == null)
                    book = new Book_Table_Data_Gateway().selectBook(isbn);
		request.setAttribute("book", book);

		RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/BookListing.jsp");
                dispatcher.forward(request, response);
	}

}
