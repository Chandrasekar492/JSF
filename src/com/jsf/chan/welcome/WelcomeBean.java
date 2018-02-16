package com.jsf.chan.welcome;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jsf.chan.db.DAO.WelcomeDAO;
import com.jsf.chan.db.DAO.WelcomeDAOImpl;
import com.jsf.chan.db.Model.User;
import com.jsf.chan.db.Utils.DBConnection;

public class WelcomeBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4938230804556741419L;

	private static final Logger logger = Logger.getLogger(WelcomeBean.class);

	private List<String> items;
	private String selectedItem;
	private User user;
	private List<User> userDetails;
	

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<User> userDetails) {
		this.userDetails = userDetails;
	}

	public WelcomeBean() {
		loadradio();
	}
	
	private void loadradio() {
		user = new User();
		items = new ArrayList<>();
		//selectedItem = "Item 1";
		items.add("Yes");
		items.add("No");
		items.add("Tied");
	}

	

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem_) {
		selectedItem = selectedItem_;
	}

	public List<String> getItems() {
		return items;
	}

	public String proccess() throws SQLException {
		
		DBConnection db = new DBConnection();
		Connection  conn = db.getConnection();
		WelcomeDAO dao = new WelcomeDAOImpl();
		int success = dao.saveDetails(conn, user);
		userDetails = dao.getDetails(conn);
		logger.info("User insertion success : " +success);
		
		String sql1 = "INSERT INTO RADIO VALUES(?)";
		PreparedStatement ptst = null;
		try {
			ptst = conn.prepareStatement(sql1);
			ptst.setString(1, selectedItem);
			int j = ptst.executeUpdate();
			logger.info("Inserted Item : "+j);
		}catch(SQLException sql) {
			sql.printStackTrace();
		}finally {
			
			if(ptst !=null) {
				ptst.close();
			}
		}
		
		return "success";
		
		
	}
}
