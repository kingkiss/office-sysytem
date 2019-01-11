package com.office.manage.domain;


public class User {
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_truename;
	private String user_phone;
	private String user_department;
	private int user_authority;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int id) {
		this.user_id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_truename() {
		return user_truename;
	}
	public void setUser_truename(String user_truename) {
		this.user_truename = user_truename;
	}
	public String getUser_department() {
		return user_department;
	}
	public void setUser_department(String user_department) {
		this.user_department = user_department;
	}
	public int getUser_authority() {
		return user_authority;
	}
	public void setUser_authority(int user_authority) {
		this.user_authority = user_authority;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
}
