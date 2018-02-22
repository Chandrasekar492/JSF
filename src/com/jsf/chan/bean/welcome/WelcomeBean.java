package com.jsf.chan.bean.welcome;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.jsf.chan.db.DAO.WelcomeDAO;
import com.jsf.chan.db.DAO.WelcomeDAOImpl;
import com.jsf.chan.db.Model.User;
import com.jsf.chan.db.Utils.DBConnection;

public class WelcomeBean implements Serializable {

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
		// selectedItem = "Item 1";
		items.add("Yes");
		items.add("No");
		items.add("Tied");
	}

	public void updategender(ActionEvent e) {
		items = new ArrayList<>();
		logger.info("Inside Logger :");
		items.add("Male");
		items.add("Female");
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

	public String deletedata() throws SQLException {
		Connection conn = null;
		try {
		DBConnection db = new DBConnection();
		conn = db.getConnection();
		WelcomeDAO dao = new WelcomeDAOImpl();
		int rowsDeleted = dao.deletedata(conn);
		logger.info("Rows Deleted by User :"+rowsDeleted);
		}finally {
			if(conn !=null) {
				conn.close();
			}
		}
		return null;
	}

	public String proccess() throws SQLException {

		DBConnection db = new DBConnection();
		Connection conn = db.getConnection();
		WelcomeDAO dao = new WelcomeDAOImpl();
		logger.info("user gender :"+user.getGender());
		int success = dao.saveDetails(conn, user);
		userDetails = dao.getDetails(conn);
		logger.info("User insertion success : " + success);

		String sql1 = "INSERT INTO RADIO VALUES(?)";
		PreparedStatement ptst = null;
		try {
			ptst = conn.prepareStatement(sql1);
			ptst.setString(1, selectedItem);
			int j = ptst.executeUpdate();
			logger.info("Inserted Item : " + j);
		} catch (SQLException sql) {
			sql.printStackTrace();
		} finally {
			if(conn !=null) {
				conn.close();
			}
			if (ptst != null) {
				ptst.close();
			}
		}

		return "success";

	}
}
