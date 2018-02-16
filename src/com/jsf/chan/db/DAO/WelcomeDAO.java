package com.jsf.chan.db.DAO;

import java.sql.Connection;
import java.util.List;

import com.jsf.chan.db.Model.User;

public interface WelcomeDAO {

	public List<User> getDetails(Connection conn);
	
	public int saveDetails(Connection connection, User user) ;
}
