package com.healthCenter.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlDataSource {

	public InsertReturnObject mySqlInsert(String query) throws ClassNotFoundException, SQLException, IOException {
		Connection con = DataBaseConnection.getInstance().getConnection();
		InsertReturnObject newRO = new InsertReturnObject();
		String queryString = query;
		System.out.println(con.toString());
		PreparedStatement ps = con.prepareStatement(queryString);

		int status = ps.executeUpdate();
		// ResultSet rs = ps.getGeneratedKeys();
		// newRO.setRs(rs);
		ps.close();

		if (status > 0) {
			newRO.setStatus(true);
		} else {
			newRO.setStatus(false);
		}
		return newRO;
	}

	public InsertReturnObject mySqlQuery(String query) throws ClassNotFoundException, SQLException, IOException {
		Connection con = DataBaseConnection.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		InsertReturnObject newRO = new InsertReturnObject();
		newRO.setRs(rs);
		newRO.setPs(ps);
		return newRO;

	}

}
