package com.bookstore.models;

import java.io.Serializable;

public class User implements Serializable {

	private String username;
	private String passwd;
	
	public User(String username, String passwd) {
		this.username = username;
		this.passwd = passwd;
	}

	public User() {
		this.username = "";
		this.passwd = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
