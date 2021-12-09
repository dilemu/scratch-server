package com.example.server.service.impl;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.model.vo.UserResult;
import com.example.server.service.IUserService;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(BodyServiceImpl.class);

    @Override
    public JsonResult login(UserRequest requestVO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("usrname", requestVO.getUserName());
        map.put("passwd", requestVO.getPassword());
        HttpEntity requestBody = new HttpEntity(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://8.133.177.11/login", requestBody, String.class);
        UserResult user = JsonUtils.jsonToObject(responseEntity.getBody(), UserResult.class);
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult getUserInfo(String token) throws Exception {
        String url = "http://8.133.177.11/userinfo/" + token;
        Map<String, String> param = new HashMap<>();
        String result = HttpUtils.get(url);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (user.getCode().equals("401")) {
            LOGGER.info("账号余额不足");
            throw new BizBaseException(Integer.valueOf(user.getCode()), "账号余额不足");
        } else if (user.getCode().equals("402")) {
            LOGGER.info("token无效，请重新登录");
            throw new BizBaseException(Integer.valueOf(user.getCode()), "token无效，请重新登录");
        }
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult verifyTimes(String token) throws Exception {
        String url = "http://8.133.177.11/ai_times/" + token;
        String result = HttpUtils.get(url);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (user.getCode().equals("401")) {
            LOGGER.info("账号余额不足");
            throw new BizBaseException(Integer.valueOf(user.getCode()), "账号余额不足");
        } else if (user.getCode().equals("402")) {
            LOGGER.info("token无效，请重新登录");
            throw new BizBaseException(Integer.valueOf(user.getCode()), "token无效，请重新登录");
        }

        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult userLogin(UserRequest requestVO) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("account", requestVO.getAccount());
        map.put("type", requestVO.getType());
        if (requestVO.getType().equals("account")) {
            map.put("password", requestVO.getCode());
        } else {
            map.put("code", requestVO.getCode());
        }
        String result = HttpUtils.post("http://iot.delightmom.com:1036/user/login", map);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (!user.getCode().equals("200")) {
            LOGGER.info(user.getMessage());
            throw new BizBaseException(user.getMessage());
        }
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult getCode(UserRequest requestVO) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", requestVO.getMobile());

        String result = HttpUtils.post("http://iot.delightmom.com:1036/user/getCode", map);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (!user.getCode().equals("200")) {
            LOGGER.info(user.getMessage());
            throw new BizBaseException(user.getMessage());
        }
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult register(UserRequest requestVO) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", requestVO.getMobile());
        map.put("password", requestVO.getCode());
        map.put("code", requestVO.getCode());

        String result = HttpUtils.post("http://iot.delightmom.com:1036/user/register", map);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (!user.getCode().equals("200")) {
            LOGGER.info(user.getMessage());
            throw new BizBaseException(user.getMessage());
        }
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult isRegister(UserRequest requestVO) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", requestVO.getMobile());

        String result = HttpUtils.post("http://iot.delightmom.com:1036/user/isRegister", map);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (!user.getCode().equals("200")) {
            LOGGER.info(user.getMessage());
            throw new BizBaseException(user.getMessage());
        }
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult getInfo(String token) throws Exception {
        String result = HttpUtils.post("http://iot.delightmom.com:1036/user/info", token);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (!user.getCode().equals("200")) {
            LOGGER.info(user.getMessage());
            throw new BizBaseException(user.getMessage());
        }
        return JsonResult.success(user.getData());
    }

    @Override
    public JsonResult getTimes(String token) throws Exception {
        String result = HttpUtils.post("http://iot.delightmom.com:1036/user/times", token);
        UserResult user = JsonUtils.jsonToObject(result, UserResult.class);
        if (user.getMessage().equals("not enough times")) {
            LOGGER.info("账号余额不足");
            throw new BizBaseException(401, "账号余额不足");
        } else if (user.getMessage().equals("token error")) {
            LOGGER.info("token无效，请重新登录");
            throw new BizBaseException(402, "token无效，请重新登录");
        }
        return JsonResult.success(user.getData());
    }
}
