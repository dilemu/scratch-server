package com.example.server.model.dto;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/20 15:48
 */
public class NowDTO<T> {
    private String code;
    private String updateTime;
    private String fxLink;
    private T now;
    private T refer;

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public T getNow() {
        return now;
    }

    public void setNow(T now) {
        this.now = now;
    }

    public T getRefer() {
        return refer;
    }

    public void setRefer(T refer) {
        this.refer = refer;
    }
}
