package com.bookstore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bookstore.DAO.Book_Table_Data_Gateway;
import com.bookstore.models.Book;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class AddBook
 */
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private static Book book = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
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

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String isbn = request.getParameter("isbn");
            String author = request.getParameter("author");
            String image = request.getParameter("coverImage");

            if (book == null)
                book = new Book(isbn,title,author,description,image);
                    
            int result = 0;
            try {
                result = new Book_Table_Data_Gateway().createNewBook(book);
            } catch (SQLException ex) {
                Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result == 1) {
                    response.sendRedirect(request.getContextPath() + "/AddBookConfirmation.jsp?success=true");
            } else {
                    response.sendRedirect(request.getContextPath() + "/AddBookConfirmation.jsp?success=false");
            }
	}

	private Book populateBook(HashMap<String, String> map) {
		Book book = new Book();
		book.setIsbn(map.get("isbn"));
		book.setTitle(map.get("title"));
		book.setDescription(map.get("description"));
		if (map.containsKey("coverImageFile")) {
			book.setCoverImageFile(map.get("coverImageFile"));
		}
		return book;
	}

}
