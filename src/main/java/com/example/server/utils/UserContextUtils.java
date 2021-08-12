package com.example.server.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <功能描述> 从上下文中获取用户账户信息
 *
 * @author
 * @date 2020/9/23 16:02
 */

public class UserContextUtils {

    private UserContextUtils() {
    }

    private static final String USER_ACCOUNT = "user-account";
    private static final String HPC_TOKEN = "Hpc-Token";
    private static final String ACCESS_TOKEN = "Access-Token";
    private static final String TOKEN = "Token";

    public static String getUserAccount() {
        return getServletRequest().getHeader(USER_ACCOUNT);
    }

    public static String getHpcToken(){
        return getServletRequest().getHeader(HPC_TOKEN);
    }

    public static String getAccessToken(){
        return getServletRequest().getHeader(ACCESS_TOKEN);
    }

    public static String getToken() {
        return getServletRequest().getHeader(TOKEN);
    }

    public static HttpServletRequest getServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getServletResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }
}
