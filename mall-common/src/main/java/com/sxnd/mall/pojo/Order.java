package com.sxnd.mall.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private int id;
    private String orderNo;//订单号
    private int orderscore;//订单积分
    private int status;//0未发货，1已发货
    private int productId;//商品表ID（mall_product）
    private int userId;//用户表ID（mall_user）
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
