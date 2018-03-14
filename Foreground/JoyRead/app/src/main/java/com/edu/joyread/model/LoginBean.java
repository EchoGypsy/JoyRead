package com.edu.joyread.model;

/**
 * Created by 87395 on 2018/3/13.
 * bean 用来封装信息
 */

public class LoginBean {
    private String sID;
    private String password;

    public String getsID() {
        return sID;
    }

    public String getPassword() {
        return password;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
