package com.example.server.service.impl;

import com.baidu.aip.nlp.AipNlp;
import com.example.server.model.vo.AffectiveTendencyVO;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NlpRequest;
import com.example.server.service.INlpService;
import com.example.server.utils.AIUtils;
import com.example.server.utils.JsonUtils;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        JSONObject res = client.lexer(nlpRequest.getText(), options);
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        List result = res.getJSONArray("items").toList();
        return JsonResult.success(result);
    }

    @Override
    public JsonResult analysisAffectiveTendency(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        JSONObject res = client.sentimentClassify(nlpRequest.getText(), options);
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

        JSONObject res = client.wordSimEmbedding(nlpRequest.getText(), nlpRequest.getText_2(), options);
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Map result = res.toMap();
        double score = (double) result.get("score");
        return JsonResult.success((int) (score * 100));
    }

    @Override
    public JsonResult recoverErrOfText(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();

        JSONObject res = client.ecnet(nlpRequest.getText(), options);
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Map resultMap = res.toMap();
        return JsonResult.success(resultMap.get("item"));
    }

    @Override
    public JsonResult classifyAddress(NlpRequest nlpRequest) {
        HashMap<String, Object> options = new HashMap<String, Object>();

        JSONObject res = client.address(nlpRequest.getText(), options);
        if (res.has("error_code"))
            return JsonResult.error(res.getInt("error_code"), res.getString("error_msg"));
        Object result = res.toMap();
        return JsonResult.success(result);
    }
}
