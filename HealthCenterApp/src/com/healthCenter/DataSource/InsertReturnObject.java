package com.healthCenter.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertReturnObject {
	
	boolean status;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	ResultSet rs;
	
	PreparedStatement ps;
	public PreparedStatement getPs() {
		return ps;
	}
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	Connection con;

}