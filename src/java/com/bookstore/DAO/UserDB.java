package com.bookstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookstore.models.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserDB {
	private String username;
	private String passwd;
        
        Object obj;    
	
        public UserDB() throws FileNotFoundException, IOException, ParseException {
            this.obj = new JSONParser().parse(new FileReader("C:\\Users\\vieth\\Desktop\\SOEN 387\\Assignment\\A2\\SOEN387_Assignment2\\user.json"));
        }
		
	/**
	 * Retrieve a model for a single user
	 * @param username case-sensitive String containing username
	 * @return model of a user
	 */
	public User selectUser(String username) throws IOException, ParseException {
            this.obj = new JSONParser().parse(new FileReader("C:\\Users\\vieth\\Desktop\\SOEN 387\\Assignment\\A2\\SOEN387_Assignment2\\user.json"));
            JSONObject jo = (JSONObject) obj;
            String user = null;
            User select_user = new User();

            for(Iterator iterator = jo.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (key.equals(username)){
                    user = key;
                }
            }

            if (user != null){
                String pass= (String) jo.get(user);

                this.username = user;
                this.passwd = pass;

                select_user = new User(username,passwd);
            }

            return select_user;
	}
}
