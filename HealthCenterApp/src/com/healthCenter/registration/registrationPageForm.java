package com.healthCenter.registration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.healthCenter.DataSource.*;

public class registrationPageForm {
	
	
	String lname;
	String age;
	String email;
	String password;
	String gender;
	String empstatus;
	String userActive;
	String userType;
	String address1;
	String address2;
	String address3;
	String city;
	String district;
	String pincode;
	String state;
	String phoneNo;
	
	
	String fname;
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmpstatus() {
		return empstatus;
	}

	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
    
	String salt = Passwordencryptionutil.getSalt(60);


	public String register() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		
		MySqlDataSource mysql = new MySqlDataSource();
		
//		String query = "insert into student values";
//				//query = query + " (\""+ this.fname +"\",\""+ this.lname +"\",\""+ this.age+"\",\""+ this.age+"\"\",)";
//				  query = query + " (\""+ this.fname +"\",\""+ this.lname +"\",\""+ this.email +"\","
//				  		+ "\""+ this.age +"\",\""+ this.password +"\",\""+ this.gender +"\",\""+ this.empstatus +"\",\""
//						+ this.userActive +"\",\""+ this.userType +"\",\""+ this.address1 +"\",\""+ this.address2 +"\",\""
//				  		+ this.address3 +"\",\""+ this.city +"\",\""+ this.district +"\",\""+ this.pincode +"\",\""+ this.state +"\",\""
//						+ this.phoneNo +"\"	)";
		
		
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sqlquery.properties");
		prop.load(inputStream);
		
		
		String insertUserQuery = prop.getProperty("createUserSql");
		String salt = Passwordencryptionutil.getSalt(10);
		
		insertUserQuery = insertUserQuery.replace(":?1",this.getEmail());
		
		insertUserQuery = insertUserQuery.replace(":?2",Passwordencryptionutil.generateSecurePassword(this.getPassword(),salt));
		
		insertUserQuery = insertUserQuery.replace(":?3","I");
		
		insertUserQuery = insertUserQuery.replace(":?4","G");
		
		insertUserQuery = insertUserQuery.replace(":?6",this.getPhoneNo());
		
		insertUserQuery = insertUserQuery.replace(":?7",this.getEmail());
		
		insertUserQuery = insertUserQuery.replace(":?8", salt);
		
		
		System.out.println(insertUserQuery + "last");
		InsertReturnObject newRO = new InsertReturnObject();
		//insertQuery = "insert into user (logonId,password,userActive,registerType,createdDate,lastUpdate,phoneNumber,emailId) values ('ashok@gmail.com1','ashok@gmail.com','I','G',now(),now(),'2121212121','ashok@gmail.com')";
		newRO = mysql.mySqlInsert(String.valueOf(insertUserQuery));
		
		if (newRO.isStatus()) {
			System.out.println("inside");
			String selectUserQuery = prop.getProperty("selectUserSql");
			String insertAddressQuery= prop.getProperty("createAddressSql");
			InsertReturnObject newROadd = new InsertReturnObject();
			selectUserQuery = selectUserQuery.replaceAll(":logonId", this.getEmail());
			newROadd = mysql.mySqlQuery(selectUserQuery);
			System.out.println(selectUserQuery + "sasas");
			ResultSet rs = newROadd.getRs();
			String value = "";
			while (rs.next())
		      {
				value = String.valueOf(rs.getObject("userId"));
		      }
			
			System.out.println(value+ "sasfffas");
			insertAddressQuery = insertAddressQuery.replace(":userId",value);
			insertAddressQuery = insertAddressQuery.replace(":address1",this.getAddress1());
			insertAddressQuery = insertAddressQuery.replace(":address2",this.getAddress2());
			insertAddressQuery = insertAddressQuery.replace(":city",this.getCity());
			insertAddressQuery = insertAddressQuery.replace(":district",this.getDistrict());
			insertAddressQuery = insertAddressQuery.replace(":state",this.getState());
			insertAddressQuery = insertAddressQuery.replace(":pincode",this.getPincode());
			System.out.println(insertAddressQuery + "insertAddressQueryLast");
			InsertReturnObject newROaddIn = new InsertReturnObject();
			newROaddIn = mysql.mySqlInsert(String.valueOf(insertAddressQuery));
			newROadd.getPs().close();
			
			if(newROaddIn.isStatus()) {
				return "success";
			}else {
				return "failed";
			}
		}else {
			return "failed";
		}
		
	}
	
}
