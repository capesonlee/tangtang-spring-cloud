package com.lijuyong.startup.auth.model;

/**
 * Created by john on 2017/3/14.
 */
public class LoginUser {
    private String user;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
