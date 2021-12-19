package com.example.server.conifg;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
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
        if(request.getMethod().toUpperCase().equals("OPTIONS")) { return true;};
        String access_token = request.getHeader("Access-Token");
        if (null == access_token) {
            throw new BizBaseException("token为空，请先登录");
        }
        
        JsonResult user = userService.getTimes(access_token);
        LOGGER.info("userInfo: " + user.getData());
        return true;
    }
}
