package com.xbp.xbp.xiangsi.bean;

import cn.bmob.v3.BmobObject;

/**
    注册用户信息
 */
public class UserMsg extends BmobObject {
    private String user_name;
    private String user_email;
    private String user_password;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id;
    private  static UserMsg userMsg;

    private  UserMsg(){

    }
    private  UserMsg(String tableName, String user_name, String user_email, String user_password) {
        super(tableName);
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public static UserMsg getInstance(String user_name,String user_email,String user_password){

        if(userMsg==null){

            synchronized (UserMsg.class){
                if(userMsg==null){
                    userMsg=new UserMsg(user_name,user_email,user_password);
                }
            }
        }
        return  userMsg;
    }

    public UserMsg(String tableName, String user_name, String user_password) {
        super(tableName);
        this.user_name = user_name;
        this.user_password = user_password;
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
}
