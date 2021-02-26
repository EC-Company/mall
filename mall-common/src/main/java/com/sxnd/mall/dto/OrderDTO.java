package com.sxnd.mall.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {

    private int id;
    private String orderNo;//订单号
    private int orderscore;//订单积分
    private int status;//0未发货，1已发货
    private String productName;//商品名称）
    private String username;//用户名
    private String consignee;//收货人
    private String phone;//收货电话
    private String address;//收货地址
    private Date createTime;//


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderscore() {
        return orderscore;
    }

    public void setOrderscore(int orderscore) {
        this.orderscore = orderscore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
