package com.example.server.conifg;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.UserResult;
import com.example.server.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);
    private final IUserService userService;

    @Autowired
    public SessionInterceptor(IUserService userService) {
        this.userService = userService;
    }

    //请求处理前调用,我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String access_token = request.getHeader("Access-Token");
        UserResult user = userService.verifyTimes(access_token);
        if (!user.getCode().equals("200")) {
            LOGGER.info("校验失败:" + user.getMsg());
            throw new BizBaseException(Integer.valueOf(user.getCode()), user.getMsg());
        } else {
            LOGGER.info("userInfo: "+user.getData());
            return true;
        }
    }
}
