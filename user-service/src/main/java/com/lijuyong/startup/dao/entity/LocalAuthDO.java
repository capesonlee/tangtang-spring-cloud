package com.lijuyong.startup.dao.entity;

/**
 * Created by john on 2017/3/13.
 */
public class LocalAuthDO {
    private  Long userId;
    private  String loginName;
    private  String password;
    private  Integer authStatus;

    public Long getUserId() {
        return userId;
    }
    public String getLoginName(){
        return  loginName;

    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
