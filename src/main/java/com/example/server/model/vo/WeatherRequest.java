package com.example.server.model.vo;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/20 14:13
 */
public class WeatherRequest {
    private String location;

    private String unit;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
