package com.bookstore.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookstore.models.Book;

public class BookDB {
	final private static String db_url = "jdbc:mysql://localhost:3306/bookstore";
	final private static String db_username = "root";
	final private static String db_passwd = "Barcelona2108";
	
	DBConnectionPool connPool = null;
	
	public BookDB() {
		this.connPool = setDBConnection();
	}
	
	/**
	 * Sets this instances DBConnector pool using internal credentials.
	 * @return the DBConnection pool upon successful connection to db.
	 */
	public DBConnectionPool setDBConnection() {
		try {
			connPool = new DBConnectionPool(new String(db_url), new String(db_username), new String(db_passwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connPool;
	}
	
	/**
	 * Creates a database entry for a new book. 
	 * @param book A fully populated Book object 
	 * @return Number of rows affected (1 for success. 0 for error)
	 */
	public int createNewBook(Book book) {	
		Statement stmt = null;
		ResultSet rs = null;
		int resultNo = 0;
		Connection conn = null;
		/*System.out.println("In createNewBook");
		System.out.println("ISBN: " + book.getIsbn());
		System.out.println("Title: " + book.getTitle());
		System.out.println("Publisher: " + book.getPublisher());
		System.out.println("Price: " + book.getPrice());
		System.out.println("Description: " + book.getDescription());
		System.out.println("Publish Date: " + book.getPublishDate());
		System.out.println("Inventory: " + book.getInventory());*/
		
		try {
			conn = connPool.getConnection();
			if(conn != null) {
				stmt = conn.createStatement();

				String strQuery = "insert into books (isbn, title, "
						+ "description, cover_image) values('"
				        + book.getIsbn() + "', '"
				        + book.getTitle() + "', '"
				        + book.getDescription() + "', '"
				        + book.getCoverImageFile() + "', '";
				resultNo = stmt.executeUpdate(strQuery);
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					connPool.returnConnection(conn);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		return resultNo;
	}
	
	/**
	 * Retrieve a model for a single book
	 * @param isbn String containing isbn with dashes.
	 * @return model of a book
	 */
	public Book selectBook(String isbn) {
		Statement stmt = null;
                System.out.println("fuck");
		ResultSet rs = null;
		Book book = new Book();
		Connection conn = null;
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.createStatement();
				
				String strQuery = "select isbn, title, description, cover_image "  
						+ "from books where isbn='" + isbn + "'";
				rs = stmt.executeQuery(strQuery);
				if (rs.next()) {
					book.setIsbn(rs.getString(1));
					book.setTitle(rs.getString(2));
					book.setDescription(rs.getString(3));
					book.setCoverImageFile(rs.getString(4));
				}
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					connPool.returnConnection(conn);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		return book;
	}
	
	/**
	 * Retrieve a model for a single book
	 * @param query String containing isbn with dashes.
	 * @return model of a book
	 */
	public ArrayList<Book> searchForBooks(String queryIn) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> books = new ArrayList<Book>();
		Connection conn = null;
		String query = queryIn.replaceAll("'", "''");
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.createStatement();
				
				String strQuery = "select b.isbn, b.title, b.description, "
						+ "b.cover_image, "  
						+ "from books b "
						+ "join book_author ba "
						+ "on b.isbn=ba.isbn "
						+ "join author a "
						+ "on a.author_id=ba.author_id "
						+ "on b.isbn=gb.isbn "
						+ "where "
						+ "b.isbn like '%" + query + "%' or "
						+ "b.title like '%" + query + "%' or "
						+ "a.author_name like '%" + query + "%' or "
						+ "b.description like '%" + query + "%'";
				System.out.println(strQuery);
				rs = stmt.executeQuery(strQuery);
				while (rs.next()) {
					Book b = new Book();
					b.setIsbn(rs.getString(1));
					b.setTitle(rs.getString(2));
					b.setDescription(rs.getString(3));
					b.setCoverImageFile(rs.getString(4));
					if (isUniqueInList(books, b.getIsbn())) {
						books.add(b);
					}
				}
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					connPool.returnConnection(conn);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		return books;
	}
	
	private boolean isUniqueInList(ArrayList<Book> books, String isbn) {
		boolean isUnique = true;
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				isUnique = false;
			}
		}
		return isUnique;
	}
	
	/**
	 * Returns an list of all users in the system. May be useful for running reports
	 * of for user administration by staff
	 * @return A list of all users in the system.
	 */
	public ArrayList<Book> selectAllBooks() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> books = new ArrayList<Book>();
		Connection conn = null;
		
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.createStatement();
				String strQuery = "select isbn, title, description, cover_image"  
						+ "from books";
				rs = stmt.executeQuery(strQuery);
				while(rs.next()) {
					Book b = new Book();
					b.setIsbn(rs.getString(1));
					b.setTitle(rs.getString(2));
					b.setDescription(rs.getString(3));
					b.setCoverImageFile(rs.getString(4));
					books.add(b);
				}
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					connPool.returnConnection(conn);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}		
		return books;
	}
	
	/**
	 * Deletes a book from the database. This probably shouldn't be used that often
	 * as many tables depend on the Books table through foreign keys and we wouldn't
	 * be able to make sense of old transaction records.
	 * @param isbn the ISBN of the book to be removed from the system
	 * @return The number of affected rows. (1 for success. 0 for error) 
	 */
	public int deleteBook(String isbn) {
		Statement stmt = null;
		ResultSet rs = null;
		int resultNo = 0;
		Connection conn = null;
		
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.createStatement();
				String strQuery = "delete from books where isbn = '" + isbn + "'";
				resultNo = stmt.executeUpdate(strQuery);
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					connPool.returnConnection(conn);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
			
		return resultNo;
	}
	
	/**
	 * Update an existing book. To update a book, retrieve their book object
	 * using selectBook() or selectBook(), edit the book's fields, and then
	 * send it back to this method.
	 * @param book An updated book object
	 * @return The number of affected rows. (1 for success. 0 for error)
	 */
	public int updateBook(Book book) {
		Statement stmt = null;
		ResultSet rs = null;
		int resultNo = 0;
		Connection conn = null;
		
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.createStatement();
				String strQuery = "update books set "
				+ "isbn='" + book.getIsbn() + "', "
				+ "title='" + book.getTitle() + "', "
				+ "description='" + book.getDescription() + "', "
				+ "cover_image='" + book.getCoverImageFile() + "', "
				+ "' where isbn='" + book.getIsbn() + "'";
				resultNo = stmt.executeUpdate(strQuery);
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					connPool.returnConnection(conn);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
			
		return resultNo;
	}
}
