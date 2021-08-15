package com.example.server.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UniversalRequest;
import com.example.server.service.ICharacterService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.JsonUtils;
import com.example.server.utils.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <功能描述>
 * 文字识别
 *
 * @author
 * @date 2021/8/12 16:55
 */
@Service
public class CharacterServiceImpl implements ICharacterService {

    private static AipOcr CLIENT = AIUtils.getOcrClient();

    @Override
    public JsonResult<CharacterResult> classifyLicensePlate(byte[] image, String multi_detect) {
        HashMap<String, String> options = new HashMap<>();
        options.put("multi_detect", multi_detect);

        JSONObject res = CLIENT.plateLicense(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if(StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(-1, result.getError_msg());
        return JsonResult.success(result);
    }

    @Override
    public JsonResult<CharacterResult> classifyNormalCharacter(byte[] image, UniversalRequest universalRequest) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", universalRequest.getLanguage_type());
        options.put("detect_direction", universalRequest.getDetect_direction());
        options.put("detect_language", universalRequest.getDetect_language());
        options.put("probability", universalRequest.getProbability());

        JSONObject res = CLIENT.basicGeneral(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if(StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(-1, result.getError_msg());
        return JsonResult.success(result);    }

    @Override
    public JsonResult<CharacterResult> classifyQRCode(byte[] image) {
        HashMap<String, String> options = new HashMap<>();

        JSONObject res = CLIENT.qrcode(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if(StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(-1, result.getError_msg());
        return JsonResult.success(result);
    }

    @Override
    public JsonResult<CharacterResult> classifyHandwritten(byte[] image, UniversalRequest universalRequest) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", universalRequest.getDetect_direction());
        options.put("recognize_granularity", universalRequest.getRecognize_granularity());
        options.put("probability", universalRequest.getProbability());

        JSONObject res = CLIENT.basicGeneral(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if(StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(-1, result.getError_msg());
        return JsonResult.success(result);
    }
}
