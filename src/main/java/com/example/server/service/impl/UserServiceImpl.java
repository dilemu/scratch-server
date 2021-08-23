package com.example.server.service.impl;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.model.vo.UserResult;
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
    public UserResult login(UserRequest requestVO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("usrname", requestVO.getUserName());
        map.put("passwd", requestVO.getPassword());
        HttpEntity requestBody = new HttpEntity(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://8.133.177.11/login", requestBody, String.class);
        return JsonUtils.jsonToObject(responseEntity.getBody(), UserResult.class);
    }

    @Override
    public UserResult getUserInfo(String token) throws Exception {
        String url = "http://8.133.177.11/userinfo/" + token;
        Map<String, String> param = new HashMap<>();
        String result = HttpUtils.get(url);

        return JsonUtils.jsonToObject(result, UserResult.class);
    }

    @Override
    public UserResult verifyTimes(String token) throws Exception {
        String url = "http://8.133.177.11/ai_times/" + token;
        String result = HttpUtils.get(url);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (user.getCode().equals("401")) {
            return UserResult.error(user.getCode(), "账号余额不足");
        } else if (user.getCode().equals("402")) {
            return UserResult.error(user.getCode(), "token无效，请重新登录");
        }

        return user;
    }
}
