package com.example.server.service.impl;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFMGGService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Service
public class FMGGServiceImpl implements IFMGGService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BodyServiceImpl.class);

    @Override
    public JsonResult classifyDrink(String image) throws Exception {
        String accessToken = AIUtils.getAuth("m3E6uK7fcLG38HdYaOsPA98c", "QmB6gAiSKtvx1u7qF2haxg0k6TISGOar");
        String url = "https://aip.baidubce.com/rpc/2.0/easydl/v1/retail/drink";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("image", image);
        String param = JsonUtils.objectToJson(paramsMap);
        StopWatch sw = new StopWatch();
        sw.start();
        String res = HttpUtils.post(url, accessToken, "application/json", param);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：快消识别,耗时： " + sw.getTotalTimeSeconds() + " s");
        Map result = (Map) JsonUtils.jsonToObject(res, Object.class);
        if (result.containsKey("error_code")) {
            throw new BizBaseException((Integer) result.get("error_code"), result.get("error_msg").toString());
        }
        return JsonResult.success(result.get("results"));
    }

}

