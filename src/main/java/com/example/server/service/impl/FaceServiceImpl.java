package com.example.server.service.impl;

import com.baidu.aip.face.AipFace;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFaceService;
import com.example.server.utils.AIUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaceServiceImpl implements IFaceService {

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
        JSONObject res = client.detect(image, imageType, options);
        JSONObject result = (JSONObject) res.get("result");
        Object object = result.getJSONArray("face_list").toList().get(0);
        Map objectMap = (Map) object;
        return JsonResult.success(objectMap.get("emotion"));
    }
}
