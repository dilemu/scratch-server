package com.example.server.service;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.WeatherRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IWeatherService {
    JsonResult getWeatherOfDays(WeatherRequest location) throws Exception;

    JsonResult getCurrentWeather(WeatherRequest weatherRequest) throws Exception;

    JsonResult getAirQuality(WeatherRequest weatherRequest) throws Exception;

    JsonResult<List> getCityList(WeatherRequest weatherRequest) throws Exception;

    JsonResult getTime(WeatherRequest weatherRequest);

    JsonResult getIndices(WeatherRequest weatherRequest) throws UnsupportedEncodingException, Exception;
}
