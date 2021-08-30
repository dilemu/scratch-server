package com.example.server.service.impl;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.*;
import com.example.server.service.ITalkService;
import com.example.server.utils.AuthUtils;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

@Service
public class TalkServiceImpl implements ITalkService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TalkServiceImpl.class);
    private static final String CLIENT_ID = "FUfaCuI9FdbnF9dvp31m9kHa";
    private static final String CLIENT_SECRET = "FwbFqM9RrVhB7gIhxLaoVuWVdT4QlfEp";


    @Override
    public JsonResult getResponse(Request request) throws Exception {
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/service/v3/chat";
        String log_id = String.valueOf((int) (Math.random() * 10000000));
        TalkRequest talkRequest = new TalkRequest();
        talkRequest.setRequest(request);
        talkRequest.setLog_id(log_id);

        // 请求参数
        String params = JsonUtils.objectToJson(talkRequest);
        String accessToken = AuthUtils.getAuth(CLIENT_ID, CLIENT_SECRET);
        StopWatch sw = new StopWatch();
        sw.start();
        String result = HttpUtils.post(talkUrl, accessToken, "application/json", params);
        sw.stop();
        LOGGER.info("只能对话返回结果:{}", result);
        LOGGER.info("调用百度接口：智能对话,耗时： " + sw.getTotalTimeSeconds() + " s");
        TalkResult talkResult = JsonUtils.jsonToObject(result, TalkResult.class);
        if (talkResult.getError_code() != 0) {
            throw new BizBaseException(talkResult.getError_code(), talkResult.getError_msg());
        }

        Map resultMap = (Map) talkResult.getResult();
        List<Object> response = (List<Object>) resultMap.get("responses");
        Map responseVO = (Map) response.get(0);

        return JsonResult.success(responseVO.get("actions"));
    }
}
