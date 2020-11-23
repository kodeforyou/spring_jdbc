package com.kodeforyou.spring.jdbc.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.InitializingBean;

public class ConnectionUtil implements InitializingBean {
	private DBConfig dbConfig;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			Class.forName(dbConfig.getDriverClass());
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public void setDbConfig(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword());
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
