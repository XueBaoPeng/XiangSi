package com.xbp.xbp.xiangsi.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/10/15.
 */
public class User  extends BmobObject{
    private String username;
    private String useremail;
    private String userpassword;
    private String word;//个人名言

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
