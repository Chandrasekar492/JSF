package com.jsf.chan.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jsf.chan.db.Model.User;

public class WelcomeDAOImpl implements WelcomeDAO {
	private static final Logger logger = Logger.getLogger(WelcomeDAOImpl.class);

	@Override
	public List<User> getDetails(Connection conn) {
		
		List<User> userlist = new ArrayList<User>();
		String sql = "SELECT ID, NAME, ADDRESS, AGE, GENDER FROM EMPLOYEE";
		try {
			
			
			PreparedStatement ptst = conn.prepareStatement(sql);
			ResultSet rs = ptst.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getLong("ID"));
				user.setName(rs.getString("NAME"));
				user.setAddress(rs.getString("ADDRESS"));
				user.setAge(rs.getInt("AGE"));
				user.setGender(rs.getString("GENDER"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return userlist;
	}

	@Override
	public int saveDetails(Connection connection, User user)   {
		
		int i = 0;
		try {
			String sql = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";
		PreparedStatement ptst = connection.prepareStatement(sql); 
		ptst.setLong(1, user.getId());
		ptst.setString(2, user.getName());
		ptst.setString(3, user.getAddress());
		ptst.setInt(4, user.getAge());
		ptst.setString(5, user.getGender());
		
		i = ptst.executeUpdate();
		logger.info("User insertion success : " +i);
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return i;
	}

	@Override
	public int deletedata(Connection conn) {
		int i = 0;
		String sql = "DELETE FROM EMPLOYEE";
		try {
			PreparedStatement ptst = conn.prepareStatement(sql);
			i = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

}
