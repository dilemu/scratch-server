package com.example.server.controller;


import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/11 13:42
 */
@Api("用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping ("/verify")
    @ApiOperation(value = "用户登录校验")
    private JsonResult<String> getToken(@RequestBody UserRequest requestVO) {
        if (StringUtils.isAnyEmpty(requestVO.getUserName(), requestVO.getPassword())) {
            return JsonResult.error("账号或密码为空");
        }

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("usrname", requestVO.getUserName());
        map.put("passwd", requestVO.getPassword());
        HttpEntity requestBody = new HttpEntity(map);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://8.133.177.11/login",requestBody, String.class);
        return JsonResult.success(responseEntity.getBody());
    }


}
