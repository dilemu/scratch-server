package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.WeatherRequest;
import com.example.server.service.IWeatherService;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/8/20 14:06
 */
@RestController
@Api("气候")
@RequestMapping("/weather")
public class WeatherController {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    private final IWeatherService weatherService;

    @Autowired
    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/day")
    @ApiOperation("逐天气候")
    private JsonResult getWeatherOfDays(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if(StringUtils.isEmpty(weatherRequest.getLocation())){
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getWeatherOfDays(weatherRequest);
    }

    @PostMapping("/now")
    @ApiOperation("实时气候")
    private JsonResult getCurrentWeather(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if(StringUtils.isEmpty(weatherRequest.getLocation())){
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getCurrentWeather(weatherRequest);
    }

    @PostMapping("/air")
    @ApiOperation("空气质量")
    private JsonResult getAirQuality(@RequestBody WeatherRequest weatherRequest) throws Exception {
        if(StringUtils.isEmpty(weatherRequest.getLocation())){
            throw new BizBaseException("城市不可为空");
        }

        return weatherService.getAirQuality(weatherRequest);
    }
}
