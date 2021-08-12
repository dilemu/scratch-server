package com.example.server.service.impl;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.service.IUserService;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/12 14:39
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public JsonResult login(UserRequest requestVO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("usrname", requestVO.getUserName());
        map.put("passwd", requestVO.getPassword());
        HttpEntity requestBody = new HttpEntity(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://8.133.177.11/login", requestBody, String.class);
        return JsonUtils.jsonToObject(responseEntity.getBody(), JsonResult.class);
    }

    @Override
    public JsonResult getUserInfo(String token) throws Exception {
        String url = "http://8.133.177.11/userinfo/" + token;
        Map<String, String> param = new HashMap<>();
        String result = HttpUtils.get(url);

        return JsonUtils.jsonToObject(result, JsonResult.class);
    }

    @Override
    public JsonResult verifyTimes(String token) throws Exception {
        String url = "http://8.133.177.11/ai_times/" + token;
        Map<String, String> param = new HashMap<>();
        String result = HttpUtils.get(url);

        return JsonUtils.jsonToObject(result, JsonResult.class);
    }
}
