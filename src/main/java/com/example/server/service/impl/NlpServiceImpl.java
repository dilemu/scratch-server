package com.example.server.service.impl;

import com.baidu.aip.nlp.AipNlp;
import com.example.server.exception.BizBaseException;
import com.example.server.model.eum.NeEnum;
import com.example.server.model.vo.*;
import com.example.server.service.INlpService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import com.example.server.utils.UserContextUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
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
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
        List resList = res.getJSONArray("items").toList();
        List<LexicalVO> resultList = new ArrayList<>();
        for (Object object : resList) {
            LexicalVO lexicalVO = new LexicalVO();
            Map objectMap = (Map) object;
            lexicalVO.setNe(NeEnum.getValue(objectMap.get("ne").toString()));
            lexicalVO.setPos(NeEnum.getValue(objectMap.get("pos").toString()));
            lexicalVO.setBasic_words(objectMap.get("basic_words"));
            lexicalVO.setByte_length((Integer) objectMap.get("byte_length"));
            lexicalVO.setByte_offset((Integer) objectMap.get("byte_offset"));
            lexicalVO.setFormal(objectMap.get("formal").toString());
            lexicalVO.setItem(objectMap.get("item").toString());
            lexicalVO.setLoc_details(objectMap.get("loc_details"));
            lexicalVO.setUri(objectMap.get("uri").toString());
            resultList.add(lexicalVO);
        }
        return JsonResult.success(resultList);
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
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
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
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
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
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
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
            throw new BizBaseException(res.getInt("error_code"), res.getString("error_msg"));
        Object result = res.toMap();
        return JsonResult.success(result);
    }

    @Override
    public JsonResult retrievalChinese(NlpRequest nlpRequest) throws Exception {
        String type = null == nlpRequest.getType() ? " " : nlpRequest.getType();
        String url = "";
        String accessToken = "";
        String res;
        SinogramVO sinogramVO = new SinogramVO();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("query", nlpRequest.getText());
        String param = JsonUtils.objectToJson(paramsMap);
        switch (type) {
            case "poetry":
                url = "http://iot.delightmom.com/api/poetry/retrieval";
                accessToken = UserContextUtils.getAccessToken();
                paramsMap.put("text", nlpRequest.getText());
                paramsMap.remove("query");
                StopWatch sw1 = new StopWatch();
                sw1.start();
                res = HttpUtils.post(url, accessToken, "application/json", JsonUtils.objectToJson(paramsMap));
                Map resMap = (Map) JsonUtils.jsonToObject(res, Object.class);
                if (!resMap.get("code").toString().equals("0")) {
                    throw new BizBaseException(Integer.parseInt(resMap.get("code").toString()), resMap.get("message").toString());
                }
                sw1.stop();
                LOGGER.info("诗词检索返回结果:{}", resMap.get("data"));
                LOGGER.info("调用delimu接口：诗词检索,耗时： " + sw1.getTotalTimeSeconds() + " s");
                return JsonResult.success(resMap.get("data"));
            default:
                accessToken = AIUtils.getAuth("y8N99V9ysUB4nEuOYU4lZGkH", "WupXP1GmYKDWT7AL0OmS3jzeHLujt3fR");
                url = "https://aip.baidubce.com/rpc/2.0/kg/v1/cognitive/chinese_search";
                StopWatch sw2 = new StopWatch();
                sw2.start();
                res = HttpUtils.post(url, accessToken, "application/json", param);
                sw2.stop();
                LOGGER.info("汉语检索返回结果:{}", res);
                LOGGER.info("调用百度接口：汉语检索,耗时： " + sw2.getTotalTimeSeconds() + " s");
                Map objectMap = (Map) JsonUtils.jsonToObject(res, Object.class);
                if (objectMap.containsKey("error_code")) {
                    throw new BizBaseException((Integer) objectMap.get("error_code"), objectMap.get("error_msg").toString());
                }
                List resultList = (List) objectMap.get("result");
                Map resultMap = (Map) resultList.get(0);
                Object object = resultMap.get("response");
                Map responseMap = (Map) object;
                sinogramVO.setAnswer(responseMap.get("answer"));
                if (responseMap.get("voice") != null) {
                    sinogramVO.setVoice(responseMap.get("voice").toString());
                }
                return JsonResult.success(sinogramVO);
        }
    }
}
