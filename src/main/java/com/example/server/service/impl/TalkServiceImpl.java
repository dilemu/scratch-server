package com.example.server.service.impl;

import com.example.server.model.vo.*;
import com.example.server.service.ITalkService;
import com.example.server.utils.AuthUtils;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/8/19 13:47
 */
@Service
public class TalkServiceImpl implements ITalkService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TalkServiceImpl.class);
    private static final String CLIENT_ID = "FUfaCuI9FdbnF9dvp31m9kHa";
    private static final String CLIENT_SECRET = "FwbFqM9RrVhB7gIhxLaoVuWVdT4QlfEp";


    @Override
    public JsonResult getResponse(Request request) throws Exception {
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/service/v3/chat";
        String log_id = String.valueOf((int) (Math.random() * 10000000));
        TalkRequest talkRequest = new TalkRequest();
        talkRequest.setRequest(request);
        talkRequest.setLog_id(log_id);


        // 请求参数
        String params = JsonUtils.objectToJson(talkRequest);
        String accessToken = AuthUtils.getAuth(CLIENT_ID, CLIENT_SECRET);
        String result = HttpUtils.post(talkUrl, accessToken, "application/json", params);
        TalkResult talkResult = JsonUtils.jsonToObject(result, TalkResult.class);
        if (talkResult.getError_code() != 0) {
            return JsonResult.error(talkResult.getError_code(), talkResult.getError_msg());
        }

        Map resultMap = (Map) talkResult.getResult();
        List<Object> response = (List<Object>) resultMap.get("responses");
        Map responseVO = (Map) response.get(0);

        return JsonResult.success(responseVO.get("actions"));
    }
}
