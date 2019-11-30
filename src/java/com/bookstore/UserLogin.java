package com.bookstore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.DAO.UserDB;
import com.bookstore.models.User;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private static User user = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
		String usernameIn = request.getParameter("username");
		String passwordIn = request.getParameter("passwd");
                System.out.println(usernameIn + " " + passwordIn);
                
		if (usernameIn == "" || passwordIn == "") {
			System.out.println("Username or password empty!");
			response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=missing");
			return;
		}
                
		if (user == null)
                    user = new User();
                
                try {
                    user = new UserDB().selectUser(usernameIn);
                } catch (ParseException ex) {
                    Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
		if (user == null) {
			System.out.println("Username not known");
			response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=inv");
			return;
		}
		
		// Made it this far, user is valid. Now compare passwords.
		String hashed = this.getHash(passwordIn);
                System.out.println(hashed + " toi di code " + user.getPasswd());
		if (hashed.equals(user.getPasswd())) {
			request.getSession().setAttribute("user", usernameIn);
		} else {
			System.out.println("Password incorrect");
			response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=inv");
			return;
		}
		System.out.println("getRequestURL: " + request.getRequestURL().toString());
		System.out.println("getServletPath: " + request.getServletPath());
		System.out.println("getContextPath: " + request.getContextPath());
		response.sendRedirect(request.getContextPath() + "/");
	}

        public static String getHash(String plainTextIn) {
		// Get plaintext password, and set hash algorithm
		String password = plainTextIn;
		String algorithm = "MD5";

		byte[] plainText = password.getBytes();  // Convert to bytes
		MessageDigest md = null;

		try { 
		    md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
		    e.printStackTrace();
		}

		md.reset(); 
		md.update(plainText);                   // Add passwd bytes to digester
		byte[] encodedPassword = md.digest();   // Hash the passwd
		
		// Iterate over the result and build a string representation
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
		    if ((encodedPassword[i] & 0xff) < 0x10) {
		        sb.append("0");
		    }
		
		    sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		
		return sb.toString();
	}
}
