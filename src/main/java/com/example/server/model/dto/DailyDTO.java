package com.example.server.model.dto;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/20 15:32
 */
public class DailyDTO<T> {
    private String code;
    private String updateTime;
    private String fxLink;
    private T daily;
    private T refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
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

    public T getDaily() {
        return daily;
    }

    public void setDaily(T daily) {
        this.daily = daily;
    }

    public T getRefer() {
        return refer;
    }

    public void setRefer(T refer) {
        this.refer = refer;
    }
}
