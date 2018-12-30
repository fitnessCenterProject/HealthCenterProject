package com.healthCenter.logon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;

import com.healthCenter.DataSource.InsertReturnObject;
import com.healthCenter.DataSource.MySqlDataSource;
import com.healthCenter.DataSource.Passwordencryptionutil;
import com.healthCenter.Utilities.HealthCenterUtil;
import com.healthCenter.myProfile.MyProfile;

public class LogonForm {
	
	
	String userName;
	JSONObject userDetails;
	String passWord;
	public JSONObject getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(JSONObject userDetails) {
		this.userDetails = userDetails;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	public String login() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		
		MyProfile myProfile = new MyProfile();
		MySqlDataSource mysql = new MySqlDataSource();
		HealthCenterUtil Utility = new HealthCenterUtil();
		
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sqlquery.properties");
		prop.load(inputStream);
		
		
		String query = prop.getProperty("loginSql");
		query = query.replace(":?1", this.getUserName());
		String password = null;
		String salt = null;
		String userId = null;
		
		
		InsertReturnObject newROUser = new InsertReturnObject();
		newROUser  = mysql.mySqlQuery(query);
		ResultSet rs = newROUser.getRs();
		System.out.println(query);
		
		while(rs.next()) {
				if (String.valueOf(rs.getObject("rowCount")).equalsIgnoreCase("1") ) {
				System.out.println("found");
				password = String.valueOf(rs.getObject("password"));
				salt = String.valueOf(rs.getObject("saltValue"));
				userId = String.valueOf(rs.getObject("userId"));
				System.out.println(this.getPassWord() + " d "+password + " d "+ salt + "ffff");
				boolean checkpass = Passwordencryptionutil.verifyUserPassword(this.getPassWord(), password, salt);
				System.out.println(checkpass);
				if(checkpass) {
					System.out.println("inside");
					String insertSession = prop.getProperty("insertSessionSql");
					insertSession = insertSession.replace(":?1", userId);
					insertSession = insertSession.replace(":?2", Utility.getCookieString());
					System.out.println(insertSession+ "inside");
					InsertReturnObject newROSes = new InsertReturnObject();
					System.out.println(String.valueOf(insertSession));
					newROSes = mysql.mySqlInsert(String.valueOf(insertSession));
					if(newROSes.isStatus()) {
						System.out.println(userId+"userId");
						myProfile.setUserId(userId);
						myProfile.excute();
						this.setUserDetails(myProfile.getUserDetails());
						return "true";
					}else {
						return "false";
					}
				}else {
					return "false";
				}
				
			}else {
				return "false";
			}		
			
		}
		return "false";
		
	}
	
	
	

}
