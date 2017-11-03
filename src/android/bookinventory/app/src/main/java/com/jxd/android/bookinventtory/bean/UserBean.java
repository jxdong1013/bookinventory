package com.jxd.android.bookinventtory.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/9.
 */

public class UserBean {
    private String username;
    //private String token;
    private String userId;
    private String phone;
    private int userid;
    private String type;
    private String realname;
    //private String createtime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

//    public String getCreatetime() {
//        return createtime;
//    }
//
//    public void setCreatetime(String createtime) {
//        this.createtime = createtime;
//    }
}
