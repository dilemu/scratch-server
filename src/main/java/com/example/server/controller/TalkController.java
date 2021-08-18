package com.example.server.controller;

import com.example.server.model.vo.*;
import com.example.server.utils.AuthUtils;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 智能对话
 *
 * @author
 * @date 2021/8/18 16:03
 */
@Api("智能对话")
@RestController
@RequestMapping("/talk")
public class TalkController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TalkController.class);
    private static final String CLIENT_ID = "FUfaCuI9FdbnF9dvp31m9kHa";
    private static final String CLIENT_SECRET = "FwbFqM9RrVhB7gIhxLaoVuWVdT4QlfEp";
    private static final String SERVICE_ID = "S56735";


    @PostMapping("/classify")
    @ApiOperation(value = "智能对话")
    private static JsonResult utterance(@RequestBody Request request) throws Exception {
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/service/v3/chat";
        String log_id = String.valueOf((int)(Math.random()*10000000));
        TalkRequest talkRequest = new TalkRequest();
        talkRequest.setRequest(request);
        talkRequest.setLog_id(log_id);
        talkRequest.setService_id(SERVICE_ID);
        talkRequest.setVersion("3.0");
        talkRequest.setSession_id("");

            // 请求参数
            String params = JsonUtils.objectToJson(talkRequest);
            String accessToken = AuthUtils.getAuth(CLIENT_ID,CLIENT_SECRET);
            String result = HttpUtils.post(talkUrl, accessToken, "application/json", params);
            TalkResult talkResult = JsonUtils.jsonToObject(result,TalkResult.class);
            if(Integer.valueOf(talkResult.getError_msg())!=0){
                return JsonResult.error(Integer.valueOf(talkResult.getError_msg()), talkResult.getError_msg());
            }

            Map resultMap = (Map) talkResult.getResult();
            List<Object> s = (List<Object>) resultMap.get("responses");
            List<Object> responses = (List<Object>) JsonUtils.jsonToObject(s.get(0).toString(), ResponseVO.class).getActions();

            return JsonResult.success(responses.get(0));
    }
}