package com.example.server.controller;


import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.model.vo.UserResult;
import com.example.server.service.IUserService;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import com.example.server.utils.StringUtils;
import com.example.server.utils.UserContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录校验")
    private UserResult getToken(@RequestBody UserRequest requestVO) {
        if (StringUtils.isAnyEmpty(requestVO.getUserName(), requestVO.getPassword())) {
            throw new BizBaseException("账号或密码为空");
        }

        return userService.login(requestVO);
    }

    @GetMapping("/info")
    @ApiOperation(value = "用户信息查询")
    private UserResult getUserInfo() throws Exception {
        String token = UserContextUtils.getAccessToken();
        if (StringUtils.isEmpty(token)) {
            throw new BizBaseException("token为空");
        }

        return userService.getUserInfo(token);
    }

    @GetMapping("/times")
    @ApiOperation(value = "用户次数验证")
    private UserResult getAITimes() throws Exception {
        String token = UserContextUtils.getAccessToken();
        if (StringUtils.isEmpty(token)) {
            throw new BizBaseException("token为空");
        }

        return userService.verifyTimes(token);
    }
}
