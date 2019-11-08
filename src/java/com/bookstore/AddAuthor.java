package com.bookstore;

import java.util.ArrayList;
import java.util.HashMap;

import com.bookstore.models.Author;

public class AddAuthor {
	
	public static ArrayList<Author> populateAuthor(HashMap<String, String> map) {
		ArrayList<Author> authors = new ArrayList<Author>();
		Author author1 = new Author();
		
		String a1 = map.get("author");
		System.out.println("author1: " + a1);
		
		if (!a1.equals("")){
			author1.setAuthorName(a1);
			authors.add(author1);
			System.out.println(author1.getAuthorName());
			System.out.println("adding a1");
		}
		return authors;
	}
}
