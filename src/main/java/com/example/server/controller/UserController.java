package com.example.server.controller;


import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.service.IUserService;
import com.example.server.utils.StringUtils;
import com.example.server.utils.UserContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("v1/login")
    @ApiOperation(value = "用户登录校验")
    private JsonResult getToken(@RequestBody UserRequest requestVO) {
        if (StringUtils.isAnyEmpty(requestVO.getUserName(), requestVO.getPassword())) {
            throw new BizBaseException("账号或密码为空");
        }

        return userService.login(requestVO);
    }

    @GetMapping("v1/info")
    @ApiOperation(value = "用户信息查询")
    private JsonResult getUserInfo() throws Exception {
        String token = UserContextUtils.getAccessToken();
        if (StringUtils.isEmpty(token)) {
            throw new BizBaseException("token为空");
        }

        return userService.getUserInfo(token);
    }

//    @GetMapping("v1/times")
//    @ApiOperation(value = "用户次数验证")
//    private JsonResult getAITimes() throws Exception {
//        String token = UserContextUtils.getAccessToken();
//        if (StringUtils.isEmpty(token)) {
//            throw new BizBaseException("token为空");
//        }
//
//        return userService.verifyTimes(token);
//    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录校验")
    private JsonResult login(@RequestBody UserRequest requestVO) throws Exception {
        if (StringUtils.isEmpty(requestVO.getAccount())) {
            throw new BizBaseException("账号不可为空");
        }
        String type = requestVO.getType();
        if (StringUtils.isEmpty(type)) {
            throw new BizBaseException("登录类型为空");
        }
        if (StringUtils.isEmpty(requestVO.getCode())) {
            if (type.equals("account")) {
                throw new BizBaseException("密码不可为空");
            } else {
                throw new BizBaseException("验证码不可为空");
            }
        }
        return userService.userLogin(requestVO);
    }

    @PostMapping("/getCode")
    @ApiOperation(value = "获取手机验证码")
    private JsonResult getCode(@RequestBody UserRequest requestVO) throws Exception {
        if (StringUtils.isEmpty(requestVO.getMobile())) {
            throw new BizBaseException("手机号码不可为空");
        }

        return userService.getCode(requestVO);
    }

    @PostMapping("/register")
    @ApiOperation(value = "手机号注册")
    private JsonResult register(@RequestBody UserRequest requestVO) throws Exception {
        if (StringUtils.isEmpty(requestVO.getMobile())) {
            throw new BizBaseException("手机号码不可为空");
        }
        if (StringUtils.isEmpty(requestVO.getCode())) {
            throw new BizBaseException("验证码不可为空");
        }
        if (StringUtils.isEmpty(requestVO.getPassword())) {
            throw new BizBaseException("密码不可为空");
        }

        return userService.register(requestVO);
    }

    @PostMapping("/isRegister")
    @ApiOperation(value = "验证手机号是否被注册")
    private JsonResult isRegister(@RequestBody UserRequest requestVO) throws Exception {
        if (StringUtils.isEmpty(requestVO.getMobile())) {
            throw new BizBaseException("手机号码不可为空");
        }

        return userService.isRegister(requestVO);
    }

    @PostMapping("/info")
    @ApiOperation(value = "用户信息查询")
    private JsonResult getInfo() throws Exception {
        String token = UserContextUtils.getAccessToken();
        if (StringUtils.isEmpty(token)) {
            throw new BizBaseException("token为空");
        }

        return userService.getInfo(token);
    }

//    @PostMapping("/times")
//    @ApiOperation(value = "用户信息查询")
//    private JsonResult getTimes() throws Exception {
//        String token = UserContextUtils.getAccessToken();
//        if (StringUtils.isEmpty(token)) {
//            throw new BizBaseException("token为空");
//        }
//
//        return userService.getTimes(token);
//    }
}
