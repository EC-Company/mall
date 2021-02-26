package com.sxnd.mall.result;

import java.io.Serializable;

public class ResultInfo implements Serializable {

    private int code = 0;

    private String msg = "ok";

    private Long count;

    private Object data;

    private static ResultInfo resultInfo = new ResultInfo();

    public static ResultInfo ok() {
        return resultInfo;
    }

    public static ResultInfo success(Object data) {
        return new ResultInfo(data);
    }

    public static ResultInfo success(Long count, Object data) {
        return new ResultInfo(count, data);
    }

    public ResultInfo() {
    }

    public ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(Object data) {
        this.data = data;
    }

    public ResultInfo(long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public static ResultInfo fail(String msg) {
        return new ResultInfo(1, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
