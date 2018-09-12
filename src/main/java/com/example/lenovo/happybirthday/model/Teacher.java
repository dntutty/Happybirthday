package com.example.lenovo.happybirthday.model;

public class Teacher {

	private int id;
	private String username;
	private String usercname;
	private String phone;
	private String dw;
	private String birthday;
	private boolean isChecked;

	public Teacher() {
		super();
	}
	public Teacher(int id, String username, String usercname, String phone,
				   String dw, String birthday) {
		super();
		this.id = id;
		this.username = username;
		this.usercname = usercname;
		this.phone = phone;
		this.dw = dw;
		this.birthday = birthday;
	}

	public Teacher(int id, String username, String usercname, String phone,
				   String dw, String birthday,boolean isChecked) {
		super();
		this.id = id;
		this.username = username;
		this.usercname = usercname;
		this.phone = phone;
		this.dw = dw;
		this.birthday = birthday;
		this.isChecked = isChecked;
	}


	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercname() {
		return usercname;
	}
	public void setUsercname(String usercname) {
		this.usercname = usercname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}
