package com.lijuyong.startup.security;

/**
 * Created by john on 2017/3/6.
 */

public class User {
    private String username;
    private Long id;
    private  String role;
    public  void setId(Long id){
        this.id =id;
    }
    public  void setUsername(String username){
        this.username = username;
    }
    public  void setRole(String role){
        this.role = role;
    }
    public String getUsername(){
        return username;
    }
    public String getRole(){
        return  role;
    }
    public Long getId(){
        return  id;
    }
}
