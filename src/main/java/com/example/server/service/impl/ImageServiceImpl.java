package com.example.server.service.impl;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.imageprocess.AipImageProcess;
import com.baidu.aip.ocr.AipOcr;
import com.example.server.model.vo.*;
import com.example.server.service.IImageService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.JsonUtils;
import com.example.server.utils.SpringUtils;
import com.example.server.utils.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/10 16:02
 */
@Service
public class ImageServiceImpl implements IImageService {

    private static AipImageClassify CLIENT = AIUtils.getImageClient();

    @Override
    public JsonResult<ImageVO> classifyGeneralImage(byte[] image) {
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = CLIENT.advancedGeneral(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object object = res.getJSONArray("result").toList().get(0);
        ImageVO resultVO = new ImageVO();
        Map entry = (Map) object;
        resultVO.setScore(Double.parseDouble(entry.get("score").toString()));
        resultVO.setName((String) entry.get("keyword"));
        return JsonResult.success(resultVO);
    }

    @Override
    public JsonResult<ImageVO> classifyAnimal(byte[] image) {
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = CLIENT.animalDetect(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object object = res.getJSONArray("result").toList().get(0);
        ImageVO resultVO = new ImageVO();
        Map entry = (Map) object;
        resultVO.setScore(Double.parseDouble(entry.get("score").toString()));
        resultVO.setName((String) entry.get("name"));
        return JsonResult.success(resultVO);
    }

    @Override
    public JsonResult<ImageVO> classifyPlant(byte[] image) {
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = CLIENT.plantDetect(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object object = res.getJSONArray("result").toList().get(0);
        ImageVO resultVO = new ImageVO();
        Map entry = (Map) object;
        resultVO.setScore(Double.parseDouble(entry.get("score").toString()));
        resultVO.setName((String) entry.get("name"));
        return JsonResult.success(resultVO);
    }

    @Override
    public JsonResult<ImageVO> classifyIngredient(byte[] image) {
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = CLIENT.ingredient(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        Object object = res.getJSONArray("result").toList().get(0);
        ImageVO resultVO = new ImageVO();
        Map entry = (Map) object;
        resultVO.setScore(Double.parseDouble(entry.get("score").toString()));
        resultVO.setName((String) entry.get("name"));
        return JsonResult.success(resultVO);
    }

    @Override
    public JsonResult classifyLandmark(byte[] image) {
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = CLIENT.landmark(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        String result = res.get("result").toString();
        return JsonResult.success(JsonUtils.jsonToObject(result, Landmark.class));
    }

    @Override
    public JsonResult<CurrencyVO> classifyCurrency(byte[] image) {
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = CLIENT.currency(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        String result = res.get("result").toString();
        return JsonResult.success(JsonUtils.jsonToObject(result, CurrencyVO.class));
    }

    @Override
    public JsonResult<FeatureVO> convertStyle(byte[] image, String style) {
        AipImageProcess client = AIUtils.getImageProcessClient();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("option", style);
        JSONObject res = client.styleTrans(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));

        FeatureVO resultVO = new FeatureVO();
        if (res.has("image"))
            resultVO.setImage((String) res.get("image"));
        resultVO.setLog_id((long) res.get("log_id"));
        return JsonResult.success(resultVO);
    }

    @Override
    public JsonResult<FeatureVO> convertAnime(byte[] image) {
        AipImageProcess client = AIUtils.getImageProcessClient();
        HashMap<String, String> options = new HashMap<>();
        JSONObject res = client.selfieAnime(image, options);
        if (res.has("error_code"))
            return JsonResult.error(-1, res.getString("error_msg"));
        FeatureVO resultVO = new FeatureVO();
        if (res.has("image"))
            resultVO.setImage((String) res.get("image"));
        resultVO.setLog_id((long) res.get("log_id"));
        return JsonResult.success(resultVO);
    }
}
