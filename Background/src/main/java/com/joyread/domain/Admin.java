package com.joyread.domain;

public class Admin {
	private int aNo;
	private String aID;
	private String password;
	
	public Admin(int aNo, String aID, String password) {
		super();
		this.aNo = aNo;
		this.aID = aID;
		this.password = password;
	}
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public String getaID() {
		return aID;
	}
	public void setaID(String aID) {
		this.aID = aID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [aNo=" + aNo + ", aID=" + aID + ", password=" + password + "]";
	}
	
}
