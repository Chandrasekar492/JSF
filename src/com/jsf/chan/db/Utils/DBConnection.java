package com.jsf.chan.db.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {

	private static Logger logger = Logger.getLogger(DBConnection.class);

	public Connection getConnection() {
		final String METHOD_NAME = "getConnection";
		logger.info("Inside Method ::" + METHOD_NAME);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

}
