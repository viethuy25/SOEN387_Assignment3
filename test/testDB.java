/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.bookstore.db.*;
import com.bookstore.models.Book;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author vieth
 */
public class testDB {
    
    public testDB() {
    }
    
    
    private ArrayList<Book> books = null;
    private Book book = null;
    
    @Test
    public void TestCreateBook()
    {
        book = new Book("765432","Life of Kaka","Ronaldo","Football","BCD");
                    
            int result = 0;
            try {
                result = new BookDB().createNewBook(book);
            }
            catch (SQLException e){
                System.out.println("error");
            }
            assertEquals(result,1);
    }
    
    @Test
    public void TestDelBook()
    {                    
            int result = 0;
            result = new BookDB().deleteBook("789546");
            assertEquals(result,1);
    }
    
    @Test
    public void TestSearchBook()
    {                    
            book = new BookDB().selectBook("765432");
            assertNotEquals(book,null);
    }
    
    @Test
    public void TestSearchBookById()
    {                    
            book = new BookDB().selectBookById(2);
            assertNotEquals(book,null);
    }
    
    @Test
    public void TestSearchAllBook()
    {                    
            books = new BookDB().selectAllBooks();
            assertNotEquals(books,null);
    }
    
    @Test
    public void TestCreateDupBook()
    {
        book = new Book("739589","Life of Kaka","Ronaldo","Football","BCD");
                    
            int result = 0;
            try {
                result = new BookDB().createNewBook(book);
            }
            catch (SQLException e){
                System.out.println("error");
            }
            assertEquals(result,0);
    }
    
    @Test
    public void TestNonRealDelBook()
    {                    
            int result = 0;
            result = new BookDB().deleteBook("123");
            assertEquals(result,0);
    }
}
