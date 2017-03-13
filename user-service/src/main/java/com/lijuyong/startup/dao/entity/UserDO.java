package com.lijuyong.startup.dao.entity;

/**
 * Created by john on 2017/3/13.
 */
public class UserDO {
    private  Long userId;
    private  String name;
    private  String cardId;
    private  String cellphone;
    private  String telephone;
    private  String email;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }
}
