package com.example.server.model.vo;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/20 14:13
 */
public class WeatherRequest {
    private String location;

    private long key;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }
}
