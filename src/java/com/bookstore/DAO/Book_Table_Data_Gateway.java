package com.bookstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookstore.models.Book;
import java.sql.PreparedStatement;

public class Book_Table_Data_Gateway {
	final private static String db_url = "jdbc:mysql://localhost:3306/bookstore";
	final private static String db_username = "root";
	final private static String db_passwd = "Barcelona2108";
	
	DBConnectionPool connPool = null;
	
	public Book_Table_Data_Gateway() {
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
	public int createNewBook(Book book) throws SQLException {	
		ResultSet rs = null;
		int resultNo = 0;
		Connection conn = null;
                PreparedStatement stmt = null;
		
		try {
			conn = connPool.getConnection();
			if(conn != null) {
                            stmt = conn.prepareStatement("insert into books (title,description,isbn,author,cover) Values(?,?,?,?,?)");
                            
                            stmt.setString(1,book.getTitle());
                            stmt.setString(2,book.getDescription());
                            stmt.setString(3,book.getIsbn());
                            stmt.setString(4,book.getAuthor());
                            stmt.setString(5,book.getCoverImageFile());
                            System.out.println(stmt);
                            resultNo = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			for(Throwable t: e) {
				t.printStackTrace();
			}
		} catch (Exception et) {
			et.printStackTrace();
		}finally {
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
	 * @param isbn String containing isbn
	 * @return model of a book
	 */
	public Book selectBook(String isbn) {
		Statement stmt = null;
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
	 * @param isbn String containing isbn with dashes.
	 * @return model of a book
	 */
	public Book selectBookById(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		Book book = null;
		Connection conn = null;
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.createStatement();
				
				String strQuery = "select * from books where id='" + id + "'";
				rs = stmt.executeQuery(strQuery);
				if (rs.next()) {
                                        book = new Book();
                                        
					book.setTitle(rs.getString(2));
					book.setDescription(rs.getString(3));
                                        book.setIsbn(rs.getString(4));
                                        book.setAuthor(rs.getString(5));
					book.setCoverImageFile(rs.getString(6));
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
		
                try{
                    if (Integer.parseInt(queryIn) > 0){
                        Book book = null;
                        book = this.selectBookById(Integer.parseInt(queryIn));
                        
                        if (book != null)
                            books.add(book);
                        else
                            System.out.println(queryIn);
                    } 
                }catch(NumberFormatException e) { 
                    System.err.println(e); 
                } catch(NullPointerException e) {
                    System.err.println(e);
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
				
				String strQuery = "select * from books";
				System.out.println(strQuery);
				rs = stmt.executeQuery(strQuery);
				while (rs.next()) {
					Book b = new Book();
					b.setTitle(rs.getString(2));
					b.setDescription(rs.getString(3));
                                        b.setIsbn(rs.getString(4));
                                        b.setAuthor(rs.getString(5));
					b.setCoverImageFile(rs.getString(6));
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int resultNo = 0;
		Connection conn = null;
		System.out.println(isbn);
		try {
			conn = connPool.getConnection();
			
			if(conn != null) {
				stmt = conn.prepareStatement("delete from books where isbn = ?");
                                stmt.setString(1, isbn);
                                
				resultNo = stmt.executeUpdate();
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
