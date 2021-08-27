package com.example.server.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.SynthesisRequest;
import com.example.server.service.IVoiceService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.HashMap;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/19 13:58
 */
@Service
public class VoiceServiceImpl implements IVoiceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoiceServiceImpl.class);
    private static AipSpeech CLIENT = AIUtils.getVoiceClient();

    @Override
    public JsonResult classifyVoice(byte[] data, String format, int rate, int dev_pid) {
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("dev_pid", dev_pid);
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject voice = CLIENT.asr(data, format, rate, options);
        sw.stop();
        LOGGER.info("返回结果:{}", voice);
        LOGGER.info("调用百度接口：语音识别,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (voice.getInt("err_no") != 0) {
            return JsonResult.error(voice.getInt("err_no"), voice.getString("err_msg"));
        }

        JSONArray result = voice.getJSONArray("result");
        return JsonResult.success(result);
    }

    @Override
    public byte[] syntheticSpeech(SynthesisRequest synthesisRequest) throws IOException {
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("aue", StringUtils.isEmpty(synthesisRequest.getAue()) ? "6" : synthesisRequest.getAue());
        options.put("spd", StringUtils.isEmpty(synthesisRequest.getSpd()) ? "5" : synthesisRequest.getSpd());
        options.put("pit", StringUtils.isEmpty(synthesisRequest.getPit()) ? "5" : synthesisRequest.getPit());
        options.put("per", StringUtils.isEmpty(synthesisRequest.getPer()) ? "4" : synthesisRequest.getPer());
        options.put("vol", StringUtils.isEmpty(synthesisRequest.getVol()) ? "5" : synthesisRequest.getVol());
        StopWatch sw = new StopWatch();
        sw.start();
        TtsResponse res = CLIENT.synthesis(synthesisRequest.getStr(), "zh", 1, options);
        sw.stop();
        LOGGER.info("返回结果:{}", res);
        LOGGER.info("调用百度接口：语音合成,耗时： " + sw.getTotalTimeSeconds() + " s");
        JSONObject result = res.getResult();//服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        if (result != null) {
            throw new BizBaseException(result.getInt("err_no"), result.getString("err_msg"));
        }

        byte[] data = res.getData();//生成的音频数据
        return data;
    }
}
