package com.lijuyong.startup.manager.dto;

/**
 * Created by john on 2017/3/27.
 */
public class UserDTO {
    private Long id;
    private  String Name;
    private String ordId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }
}
