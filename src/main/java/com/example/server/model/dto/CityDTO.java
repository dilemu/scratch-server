package com.example.server.model.dto;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/20 15:12
 */
public class CityDTO<T> {
    private String code;
    private T location;
    private T refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getLocation() {
        return location;
    }

    public void setLocation(T location) {
        this.location = location;
    }

    public T getRefer() {
        return refer;
    }

    public void setRefer(T refer) {
        this.refer = refer;
    }
}
