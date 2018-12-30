package com.healthCenter.myProfile;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import com.healthCenter.Utilities.HealthCenterUtil;
import com.google.gson.GsonBuilder;
import com.healthCenter.DataSource.InsertReturnObject;
import com.healthCenter.DataSource.MySqlDataSource;

public class MyProfile {
	
	
	String userId;
	JSONObject userDetails;
	HealthCenterUtil utility = new HealthCenterUtil();
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public JSONObject getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(JSONObject userDetails) {
		this.userDetails = userDetails;
	}
	
	public void excute() throws ClassNotFoundException, SQLException, IOException{
		JSONObject userDetailsVar = new JSONObject();
		
		String query = utility.getPropertySql("userSql");
		MySqlDataSource mysql = new MySqlDataSource();
		System.out.println(this.getUserId()+"userId");
		query = query.replace(":userId", this.getUserId());
		InsertReturnObject newROuser = new InsertReturnObject();
		System.out.println(query);
		newROuser = mysql.mySqlQuery(query);
		ResultSet rs = newROuser.getRs();
		while (rs.next())
	      {
			
			System.out.println(rs.getMetaData().getColumnCount() + "coutn");
			for(int i=1;i < rs.getMetaData().getColumnCount();i++){
				String colName = rs.getMetaData().getColumnLabel(i);
				String colValue = String.valueOf(rs.getObject(i));
				System.out.println(colName +":"+colValue);
				userDetailsVar.append(colName,colValue);
			}
	      }
		System.out.println(userDetailsVar.toString());
		this.setUserDetails(userDetailsVar);
	}
	
	

}
