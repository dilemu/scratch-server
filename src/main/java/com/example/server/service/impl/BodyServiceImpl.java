package com.example.server.service.impl;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.example.server.exception.BizBaseException;
import com.example.server.model.eum.GestureEnum;
import com.example.server.model.vo.ImageVO;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IBodyService;
import com.example.server.utils.AIUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Service
public class BodyServiceImpl implements IBodyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BodyServiceImpl.class);
    private static AipBodyAnalysis client = AIUtils.getBodyClient();

    @Override
    public JsonResult getKeysOfBody(byte[] image) {
        HashMap<String, String> options = new HashMap<>();

        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.bodyAnalysis(image, options);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：人体关键点,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code")) {
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
        }

        Object object = res.getJSONArray("person_info").toList().get(0);
        Map result = (Map) object;
        return JsonResult.success(result.get("body_parts"));
    }

    @Override
    public JsonResult getCharacteristicsOfBody(byte[] image, String type) {
        HashMap<String, String> options = new HashMap<>();

        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.bodyAttr(image, options);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：人体特征,耗时： " + sw.getTotalTimeSeconds()+ " s");

        if (res.has("error_code"))
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));

        Object result = res.getJSONArray("person_info").toList().get(0);
        Map resultMap = (Map) result;
        return JsonResult.success(resultMap.get("attributes"));
    }

    @Override
    public JsonResult getNumbersOfBody(byte[] image) {
        HashMap<String, String> options = new HashMap<>();
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.bodyNum(image, options);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：人流量,耗时： " + sw.getTotalTimeSeconds()+ " s");

        if (res.has("error_code"))
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
        return JsonResult.success(res.get("person_num"));
    }

    @Override
    public JsonResult distinguishGesture(byte[] image) {
        HashMap<String, String> options = new HashMap<>();
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.gesture(image, options);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：手势识别,耗时： " + sw.getTotalTimeSeconds()+ " s");
        if (res.has("error_code"))
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));

        Object object = res.getJSONArray("result").toList().get(0);
        Map resultMap = (Map) object;
        ImageVO imageVO = new ImageVO();
        imageVO.setName(GestureEnum.valueOf(resultMap.get("classname").toString()).getName());
        imageVO.setScore((Double) resultMap.get("probability"));

        return JsonResult.success(imageVO);
    }
}
