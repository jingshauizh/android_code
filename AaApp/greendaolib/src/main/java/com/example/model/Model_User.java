package com.example.model;

import java.util.Date;

/**
 * Created by Administrator on 16-4-17.
 */
public class Model_User {

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

    private Integer userId = 0;
    private UserStatus status = UserStatus.USE;
    private Date createDate=new Date();



    public enum UserStatus{
        DET, //删除
        USE,   // 正常
        DIS // 禁用
    }
}
