package com.example.server.service;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.WeatherRequest;

public interface IWeatherService {
    JsonResult getWeatherOfDays(WeatherRequest location) throws Exception;

    JsonResult getCurrentWeather(WeatherRequest weatherRequest) throws Exception;

    JsonResult getAirQuality(WeatherRequest weatherRequest) throws Exception;
}
