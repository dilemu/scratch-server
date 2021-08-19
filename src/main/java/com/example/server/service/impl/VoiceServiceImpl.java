package com.example.server.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IVoiceService;
import com.example.server.utils.AIUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/8/19 13:58
 */
@Service
public class VoiceServiceImpl implements IVoiceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoiceServiceImpl.class);
    private static AipSpeech CLIENT = AIUtils.getVoiceClient();

    @Override
    public JsonResult classifyVoice(byte[] data, String format, int rate) {
        JSONObject voice = CLIENT.asr(data, format, rate, null);
        if(voice.getInt("err_no") != 0){
            return JsonResult.error(voice.getInt("err_no"), voice.getString("err_msg"));
        }

        JSONArray result = voice.getJSONArray("result");
        return JsonResult.success(result);
    }

    @Override
    public JsonResult syntheticSpeech(String str) throws IOException {
        TtsResponse res = CLIENT.synthesis(str, "zh", 1, null);
        JSONObject result = res.getResult();//服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        if(result != null){
            return JsonResult.error(result.getInt("err_no"), result.getString("err_msg"));
        }

        byte[] data = res.getData();//生成的音频数据
        return JsonResult.success(data);
    }
}
