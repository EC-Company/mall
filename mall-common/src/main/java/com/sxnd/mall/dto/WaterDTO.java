package com.sxnd.mall.dto;

import java.io.Serializable;

public class WaterDTO implements Serializable {

    private int id;

    private int userId;

    private int type;//0:新用户赠送；1:邀请奖励；-1：兑换商品

    private int status;//1新增、-1扣除

    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
