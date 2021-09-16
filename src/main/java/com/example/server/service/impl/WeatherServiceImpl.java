package com.example.server.service.impl;

import com.example.server.exception.BizBaseException;
import com.example.server.model.dto.AirDTO;
import com.example.server.model.dto.CityDTO;
import com.example.server.model.dto.DailyDTO;
import com.example.server.model.dto.NowDTO;
import com.example.server.model.eum.WeatherErrorCodeEnum;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.WeatherRequest;
import com.example.server.service.IWeatherService;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/20 14:20
 */
@Service
public class WeatherServiceImpl implements IWeatherService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final String key = "0034a65bd228446d97d65b32f15cf9cc";

    @Override
    public JsonResult getWeatherOfDays(WeatherRequest weatherRequest) throws Exception {
        String url = "https://devapi.qweather.com/v7/weather/3d?" + "location=" + URLEncoder.encode(weatherRequest.getLocation(), "utf-8") + "&key=" + key + "&unit=" + weatherRequest.getUnit();
        StopWatch sw = new StopWatch();
        sw.start();
        String result = HttpUtils.get(url);
        sw.stop();
        LOGGER.info("当天天气返回结果:{}", result);
        LOGGER.info("调用和风天气接口：当天天气,耗时： " + sw.getTotalTimeSeconds() + " s");
        DailyDTO dailyDTO = JsonUtils.jsonToObject(result, DailyDTO.class);
        if (Integer.valueOf(dailyDTO.getCode()) != 200) {
            WeatherErrorCodeEnum error = WeatherErrorCodeEnum.getError(Integer.valueOf(dailyDTO.getCode()));
            throw new BizBaseException(error.getCode(), error.getMsg());
        }
        ArrayList weatherList = (ArrayList) dailyDTO.getDaily();
        return JsonResult.success(weatherList.get(0));
    }

    @Override
    public JsonResult getCurrentWeather(WeatherRequest weatherRequest) throws Exception {
        String url = "https://devapi.qweather.com/v7/weather/now?" + "location=" + URLEncoder.encode(weatherRequest.getLocation(), "utf-8") + "&key=" + key + "&unit=" + weatherRequest.getUnit();
        StopWatch sw = new StopWatch();
        sw.start();
        String result = HttpUtils.get(url);
        sw.stop();
        LOGGER.info("实时天气返回结果:{}", result);
        LOGGER.info("调用和风天气接口：当实时天气,耗时： " + sw.getTotalTimeSeconds() + " s");
        NowDTO nowDTO = JsonUtils.jsonToObject(result, NowDTO.class);
        if (Integer.valueOf(nowDTO.getCode()) != 200) {
            WeatherErrorCodeEnum error = WeatherErrorCodeEnum.getError(Integer.valueOf(nowDTO.getCode()));
            throw new BizBaseException(error.getCode(), error.getMsg());
        }
        return JsonResult.success(nowDTO.getNow());
    }

    @Override
    public JsonResult getAirQuality(WeatherRequest weatherRequest) throws Exception {
        String url = "https://devapi.qweather.com/v7/air/now?" + "location=" + URLEncoder.encode(weatherRequest.getLocation(), "utf-8") + "&key=" + key;
        StopWatch sw = new StopWatch();
        sw.start();
        String result = HttpUtils.get(url);
        sw.stop();
        LOGGER.info("空气质量返回结果:{}", result);
        LOGGER.info("调用和风天气接口：空气质量,耗时： " + sw.getTotalTimeSeconds() + " s");
        AirDTO airDTO = JsonUtils.jsonToObject(result, AirDTO.class);
        if (Integer.valueOf(airDTO.getCode()) != 200) {
            WeatherErrorCodeEnum error = WeatherErrorCodeEnum.getError(Integer.valueOf(airDTO.getCode()));
            throw new BizBaseException(error.getCode(), error.getMsg());
        }
        return JsonResult.success(airDTO.getNow());
    }

    @Override
    public JsonResult<List> getCityList(WeatherRequest weatherRequest) throws Exception {
        String cityUrl = "https://geoapi.qweather.com/v2/city/lookup?" + "location=" + URLEncoder.encode(weatherRequest.getLocation(), "utf-8") + "&key=" + key;
        StopWatch sw = new StopWatch();
        sw.start();
        String result = HttpUtils.get(cityUrl);
        sw.stop();
        LOGGER.info("城市列表返回结果:{}", result);
        LOGGER.info("调用和风天气接口：城市列表,耗时： " + sw.getTotalTimeSeconds() + " s");
        CityDTO cityDTO = JsonUtils.jsonToObject(result, CityDTO.class);
        if (Integer.valueOf(cityDTO.getCode()) != 200) {
            WeatherErrorCodeEnum error = WeatherErrorCodeEnum.getError(Integer.valueOf(cityDTO.getCode()));
            throw new BizBaseException(error.getCode(), error.getMsg());
        }

        List cityList = (List) cityDTO.getLocation();
        return JsonResult.success(cityList);
    }

    @Override
    public JsonResult getTime(WeatherRequest weatherRequest) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(weatherRequest.getTimeZone()));
        cal.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return JsonResult.success(date + " " + time);
    }

    @Override
    public JsonResult getIndices(WeatherRequest weatherRequest) throws Exception {
        String url = "https://devapi.qweather.com/v7/indices/1d?" + "type=" + weatherRequest.getType() + "&location=" + URLEncoder.encode(weatherRequest.getLocation(), "utf-8") + "&key=" + key;
        StopWatch sw = new StopWatch();
        sw.start();
        String result = HttpUtils.get(url);
        sw.stop();
        LOGGER.info("紫外线返回结果:{}", result);
        LOGGER.info("调用和风天气接口：紫外线,耗时： " + sw.getTotalTimeSeconds() + " s");
        DailyDTO dailyDTO = JsonUtils.jsonToObject(result, DailyDTO.class);
        if (Integer.valueOf(dailyDTO.getCode()) != 200) {
            WeatherErrorCodeEnum error = WeatherErrorCodeEnum.getError(Integer.valueOf(dailyDTO.getCode()));
            throw new BizBaseException(error.getCode(), error.getMsg());
        }
        ArrayList indicesList = (ArrayList) dailyDTO.getDaily();
        return JsonResult.success(indicesList.get(0));
    }
}
