package com.bookstore.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.bookstore.db.AuthorDB;

public class Book implements Serializable {
	private String isbn;
	private String title;
        private String author;
	private String description;
	private String coverImageFile;
	
	public Book(String isbn, String title, String author, String description, String coverImageFile) {
		this.isbn = isbn;
		this.title = title;
                this.author = author;
		this.description = description;
		this.coverImageFile = coverImageFile;
	}
	
	public Book() {
		this.isbn = "";
		this.title = "";
                this.author = "";
		this.description = "";
		this.coverImageFile = "";
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoverImageFile() {
		return coverImageFile;
	}

	public void setCoverImageFile(String coverImageFile) {
		this.coverImageFile = coverImageFile;
	}

        public void setAuthor(String author){
                this.author = author;
        }
        public String getAuthor() {               
		return author;
	}
}
