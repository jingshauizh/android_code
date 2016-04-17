package com.example.aa.aaapp.model;

import java.util.Date;

/**
 * Created by Administrator on 16-4-17.
 */
public class User {

    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private Integer userId;
    private UserStatus status;
    private Date createDate=new Date();



    public enum UserStatus{
        USER_DELETED, //删除
        USER_INUSE,   // 正常
        USER_DISABLE // 禁用
    }
}
