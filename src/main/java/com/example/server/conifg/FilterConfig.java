/*
package com.example.server.conifg;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserResult;
import com.example.server.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Component
public class FilterConfig implements Filter {

    private final IUserService userService;
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/user/login", "/user/info", "/user/times")));

    public FilterConfig(IUserService userService) {
        this.userService = userService;
    }

    */
/**
     * 过滤器拦截请求取出header数据放入上下文
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     *//*

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if (!allowedPath) {
            //获取head里的token
            String access_token = request.getHeader("Access-Token");
            try {
                UserResult user = userService.verifyTimes(access_token);
                if(!user.getCode().equals("200")){
                    throw new BizBaseException(Integer.valueOf(user.getCode()), user.getMsg());
                }
                if(user.getData()!= null){
                    Map data = (Map) user.getData();
                    requestAttribute.setAttribute("userName", data.get("username").toString(), 0);
                }
                requestAttribute.setAttribute("message", user.getMsg(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
*/
