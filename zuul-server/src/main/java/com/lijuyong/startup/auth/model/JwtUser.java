package com.lijuyong.startup.auth.model;

/**
 * Created by john on 2017/3/9.
 */
public class JwtUser {
    private String name;//这个是用户的真实姓名
    private Long id;//用户的唯一标识
    private Long roleId;//用户的角色id
    private Long orgId;//用户的企业编号

    public Long getId() {
        return id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
