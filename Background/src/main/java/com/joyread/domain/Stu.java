package com.joyread.domain;

import java.util.Date;

public class Stu {
	private int sNo;
	private String sID;
	private String password;
	private String sName;
	private Date date;
	private String portrait;
	private String nickname;
	private String tel;
	private int gender;
	private String eMail;
	private String IDcard;

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getIDcard() {
		return IDcard;
	}

	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}

	
	public Stu() {
		super();
	}

	public Stu(int sNo, String sID, String password, String sName, Date date, String portrait, String nickname,
			String tel, int gender, String eMail, String iDcard) {
		super();
		this.sNo = sNo;
		this.sID = sID;
		this.password = password;
		this.sName = sName;
		this.date = date;
		this.portrait = portrait;
		this.nickname = nickname;
		this.tel = tel;
		this.gender = gender;
		this.eMail = eMail;
		IDcard = iDcard;
	}

	@Override
	public String toString() {
		return "Stu [sNo=" + sNo + ", sID=" + sID + ", password=" + password + ", sName=" + sName + ", date=" + date
				+ ", portrait=" + portrait + ", nickname=" + nickname + ", tel=" + tel + ", gender=" + gender
				+ ", eMail=" + eMail + ", IDcard=" + IDcard + "]";
	}

}
