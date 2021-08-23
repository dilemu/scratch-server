package com.example.server.service.impl;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.example.server.model.eum.Gesture;
import com.example.server.model.vo.ImageVO;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IBodyService;
import com.example.server.utils.AIUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BodyServiceImpl implements IBodyService {
    private static AipBodyAnalysis client = AIUtils.getBodyClient();

    @Override
    public JsonResult getKeysOfBody(byte[] image) {
        HashMap<String, String> options = new HashMap<>();

        JSONObject res = client.bodyAnalysis(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object object = res.getJSONArray("person_info").toList().get(0);
        Map result = (Map) object;
        return JsonResult.success(result.get("body_parts"));
    }

    @Override
    public JsonResult getCharacteristicsOfBody(byte[] image, String tyle) {
        HashMap<String, String> options = new HashMap<>();
        options.put("type", tyle);

        JSONObject res = client.bodyAttr(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object result = res.getJSONArray("person_info").toList().get(0);
        Map resultMap = (Map) result;
        return JsonResult.success(resultMap.get("attributes"));
    }

    @Override
    public JsonResult getNumbersOfBody(byte[] image) {
        HashMap<String, String> options = new HashMap<>();

        JSONObject res = client.bodyNum(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));
        return JsonResult.success(res.get("person_num"));
    }

    @Override
    public JsonResult distinguishGesture(byte[] image) {
        HashMap<String, String> options = new HashMap<>();

        JSONObject res = client.gesture(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object object = res.getJSONArray("result").toList().get(0);
        Map resultMap = (Map) object;
        ImageVO imageVO = new ImageVO();

        imageVO.setName(Gesture.valueOf(resultMap.get("classname").toString()).getName());
        imageVO.setScore((Double) resultMap.get("probability"));

        return JsonResult.success(imageVO);
    }
}
