package com.example.server.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.LicensePlateVO;
import com.example.server.model.vo.UniversalRequest;
import com.example.server.service.ICharacterService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.JsonUtils;
import com.example.server.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public JsonResult classifyLicensePlate(byte[] image, String multi_detect) {
        HashMap<String, String> options = new HashMap<>();
        options.put("multi_detect", multi_detect);

        JSONObject res = CLIENT.plateLicense(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if (StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(Integer.valueOf(result.getError_code()), result.getError_msg());
        Map resultMap = (Map) result.getWords_result();
        List<Object> probability = (List<Object>) resultMap.get("probability");

        LicensePlateVO licensePlateVO = new LicensePlateVO();
        licensePlateVO.setNumber(resultMap.get("number").toString());
        licensePlateVO.setColor(resultMap.get("color").toString());
        licensePlateVO.setProbability((Double) probability.get(0));
        return JsonResult.success(licensePlateVO);
    }

    @Override
    public JsonResult classifyNormalCharacter(byte[] image, UniversalRequest universalRequest) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", universalRequest.getLanguage_type());
        options.put("detect_direction", universalRequest.getDetect_direction());
        options.put("detect_language", universalRequest.getDetect_language());
        options.put("probability", universalRequest.getProbability());

        JSONObject res = CLIENT.basicGeneral(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if (StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(Integer.valueOf(result.getError_code()), result.getError_msg());

        return JsonResult.success(result.getWords_result());
    }

    @Override
    public JsonResult classifyQRCode(byte[] image) {
        HashMap<String, String> options = new HashMap<>();

        JSONObject res = CLIENT.qrcode(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if (StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(Integer.valueOf(result.getError_code()), result.getError_msg());
        return JsonResult.success(result.getCodes_result());
    }

    @Override
    public JsonResult classifyHandwritten(byte[] image, UniversalRequest universalRequest) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", universalRequest.getDetect_direction());
        options.put("recognize_granularity", universalRequest.getRecognize_granularity());
        options.put("probability", universalRequest.getProbability());

        JSONObject res = CLIENT.basicGeneral(image, options);
        CharacterResult result = JsonUtils.jsonToObject(res.toString(2), CharacterResult.class);
        if (StringUtils.isNotEmpty(result.getError_code()))
            return JsonResult.error(Integer.valueOf(result.getError_code()), result.getError_msg());
        return JsonResult.success(result.getWords_result());
    }
}