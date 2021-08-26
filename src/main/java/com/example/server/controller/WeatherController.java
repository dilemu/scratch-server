package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.WeatherRequest;
import com.example.server.service.IWeatherService;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("气候")
@RequestMapping("/weather")
public class WeatherController {
    private final IWeatherService weatherService;

    @Autowired
    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/day")
    @ApiOperation("逐天气候")
    private JsonResult getWeatherOfDays(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if (StringUtils.isEmpty(weatherRequest.getLocation())) {
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getWeatherOfDays(weatherRequest);
    }

    @PostMapping("/now")
    @ApiOperation("实时气候")
    private JsonResult getCurrentWeather(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if (StringUtils.isEmpty(weatherRequest.getLocation())) {
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getCurrentWeather(weatherRequest);
    }

    @PostMapping("/air")
    @ApiOperation("空气质量")
    private JsonResult getAirQuality(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if (StringUtils.isEmpty(weatherRequest.getLocation())) {
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getAirQuality(weatherRequest);
    }

    @PostMapping("/city")
    @ApiOperation("城市")
    private JsonResult<List> getCityList(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if (StringUtils.isEmpty(weatherRequest.getLocation())) {
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getCityList(weatherRequest);
    }

    @PostMapping("/time")
    @ApiOperation("时间")
    private JsonResult getTime(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if (StringUtils.isEmpty(weatherRequest.getLocation())) {
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getTime(weatherRequest);
    }
}
