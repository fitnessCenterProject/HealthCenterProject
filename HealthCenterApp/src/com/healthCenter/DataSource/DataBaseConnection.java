package com.healthCenter.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

	private static DataBaseConnection instance;
	private Connection connection;

	public DataBaseConnection() throws SQLException, IOException {
		
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			Class.forName("com.mysql.jdbc.Driver");	
			this.connection = DriverManager.getConnection(prop.getProperty("jdbcConnectionString"),
					prop.getProperty("jdbcConnectionUserName"), prop.getProperty("jdbcConnectionPass"));
		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		}
		
		// TODO Auto-generated constructor stub
	}

	

	
	public Connection getConnection() {
		return connection;
	}

	public static void closeConnection(Connection con) throws SQLException {
		con.close();
	}

	public static DataBaseConnection getInstance() throws SQLException, IOException {
		
		if (instance == null) {	
			instance = new DataBaseConnection();
		} else if (instance.getConnection().isClosed()) {	
			instance = new DataBaseConnection();
		}
		return instance;
	}
}
