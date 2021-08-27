package com.example.server.service.impl;

import com.baidu.aip.face.AipFace;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFaceService;
import com.example.server.utils.AIUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaceServiceImpl implements IFaceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BodyServiceImpl.class);

    @Override
    public JsonResult classifyFace(String image) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "emotion");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        String imageType = "BASE64";

        // 人脸检测
        AipFace client = AIUtils.getFaceClient();
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.detect(image, imageType, options);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：人脸检测,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        JSONObject result = (JSONObject) res.get("result");
        Object object = result.getJSONArray("face_list").toList().get(0);
        Map objectMap = (Map) object;
        return JsonResult.success(objectMap.get("emotion"));
    }
}
