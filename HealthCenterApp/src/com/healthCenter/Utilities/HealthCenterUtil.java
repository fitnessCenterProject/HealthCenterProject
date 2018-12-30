package com.healthCenter.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class HealthCenterUtil {
	
	public String getCookieString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	public String getPropertySql(String sqlKey) throws IOException{
		String sql = null;
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sqlquery.properties");
		prop.load(inputStream);
		sql = prop.getProperty(sqlKey);
		inputStream.close();
		return sql;
	}

}
