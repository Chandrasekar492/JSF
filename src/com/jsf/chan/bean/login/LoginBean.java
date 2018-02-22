package com.jsf.chan.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jsf.chan.db.Utils.DBConnection;

public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6129020509516018928L;
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(LoginBean.class);
	// private static final long serialVersionUID = 497481828226457314L;
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String gethellowelcome() {
		return "Welcome the name : " + name;
	}

	public String insertDB() {
		DBConnection db = new DBConnection();
		try {
			
			Connection conn = db.getConnection();
			logger.info(conn);
			try {

				/*PreparedStatement ps = conn.prepareStatement("insert into chandru values (?,?)");
				ps.setString(1, name);
				ps.setString(2, password);
				int i = ps.executeUpdate();
				logger.info("Sucesfully inserted  rows:" + i);*/
				String sql = "select username, password from chandru";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					logger.info("User :" + rs.getString("USERNAME"));
					logger.info(" Pass :" + rs.getString("PASSWORD"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.close();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

}
