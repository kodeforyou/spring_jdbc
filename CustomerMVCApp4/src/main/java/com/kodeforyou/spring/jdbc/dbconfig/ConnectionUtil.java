package com.kodeforyou.spring.jdbc.dbconfig;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil{
	private DataSource dataSource;
	{
		try {
			Context initContext = new InitialContext();
			// How to get envContext?
			Context envContext = (Context) initContext.lookup("java:comp/env");
			// How to get DataSource objects?
			dataSource = (DataSource) envContext.lookup("jdbc/mysqlds");
		} catch (NamingException nme) {
			nme.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}