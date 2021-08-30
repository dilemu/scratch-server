package com.example.server.service.impl;

import com.baidu.aip.nlp.AipNlp;
import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.AffectiveTendencyVO;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NlpRequest;
import com.example.server.model.vo.SinogramVO;
import com.example.server.service.INlpService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NlpServiceImpl implements INlpService {
    private final static Logger LOGGER = LoggerFactory.getLogger(NlpServiceImpl.class);
    private final AipNlp client = AIUtils.getNlpClient();

    @Override
    public JsonResult analysisLexical(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 词法分析
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.lexer(nlpRequest.getText(), options);
        sw.stop();
        LOGGER.info("词法分析返回结果:{}", res);
        LOGGER.info("调用百度接口：词法分析,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        List result = res.getJSONArray("items").toList();
        return JsonResult.success(result);
    }

    @Override
    public JsonResult analysisAffectiveTendency(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.sentimentClassify(nlpRequest.getText(), options);
        sw.stop();
        LOGGER.info("情感倾向分析返回结果:{}", res);
        LOGGER.info("调用百度接口：情感倾向分析,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Object resultObject = res.getJSONArray("items").toList().get(0);
        Map objectMap = (Map) resultObject;
        AffectiveTendencyVO affectiveTendencyVO = new AffectiveTendencyVO();

        int sentiment = (int) objectMap.get("sentiment");
        switch (sentiment) {
            case 0:
                affectiveTendencyVO.setSentiment("消极");
                break;
            case 1:
                affectiveTendencyVO.setSentiment("中性");
                break;
            case 2:
                affectiveTendencyVO.setSentiment("积极");
                break;
            default:
                affectiveTendencyVO.setSentiment("");

        }
        affectiveTendencyVO.setProbability((Double) objectMap.get("positive_prob"));
        return JsonResult.success(affectiveTendencyVO);
    }

    @Override
    public JsonResult analysisSemanticSimilarity(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.wordSimEmbedding(nlpRequest.getText(), nlpRequest.getText_2(), options);
        sw.stop();
        LOGGER.info("词义相似度返回结果:{}", res);
        LOGGER.info("调用百度接口：词义相似度,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Map result = res.toMap();
        double score = (double) result.get("score");
        return JsonResult.success((int) (score * 100));
    }

    @Override
    public JsonResult recoverErrOfText(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.ecnet(nlpRequest.getText(), options);
        sw.stop();
        LOGGER.info("文本纠错返回结果:{}", res);
        LOGGER.info("调用百度接口：文本纠错,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Map resultMap = res.toMap();
        return JsonResult.success(resultMap.get("item"));
    }

    @Override
    public JsonResult classifyAddress(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject res = client.address(nlpRequest.getText(), options);
        sw.stop();
        LOGGER.info("地址识别返回结果:{}", res);
        LOGGER.info("调用百度接口：地址识别,耗时： " + sw.getTotalTimeSeconds() + " s");
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Object result = res.toMap();
        return JsonResult.success(result);
    }

    @Override
    public JsonResult retrievalChinese(NlpRequest nlpRequest) throws Exception {
        String accessToken = AIUtils.getAuth("y8N99V9ysUB4nEuOYU4lZGkH", "WupXP1GmYKDWT7AL0OmS3jzeHLujt3fR");
        String url = "https://aip.baidubce.com/rpc/2.0/kg/v1/cognitive/chinese_search";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("query", nlpRequest.getText());
        String param = JsonUtils.objectToJson(paramsMap);
        StopWatch sw = new StopWatch();
        sw.start();
        String res = HttpUtils.post(url, accessToken, "application/json", param);
        sw.stop();
        LOGGER.info("汉语检索返回结果:{}", res);
        LOGGER.info("调用百度接口：汉语检索,耗时： " + sw.getTotalTimeSeconds() + " s");
        Map objectMap = (Map) JsonUtils.jsonToObject(res, Object.class);
        if (objectMap.containsKey("error_code")) {
            return JsonResult.error((Integer) objectMap.get("error_code"), objectMap.get("error_msg").toString());
        }
        List resultList = (List) objectMap.get("result");
        Map resultMap = (Map) resultList.get(0);
        Object object = resultMap.get("response");
        Map responseMap = (Map) object;
        SinogramVO sinogramVO = new SinogramVO();
        sinogramVO.setAnswer(responseMap.get("answer"));
        if (responseMap.get("voice") != null) {
            sinogramVO.setVoice(responseMap.get("voice").toString());
        }
        return JsonResult.success(sinogramVO);
    }
}
