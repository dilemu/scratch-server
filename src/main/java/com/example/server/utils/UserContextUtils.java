package com.example.server.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


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

    public static String getUserAccount() {
        return getServletRequest().getHeader(USER_ACCOUNT);
    }

    public static String getHpcToken() {
        return getServletRequest().getHeader(HPC_TOKEN);
    }

    public static String getAccessToken() {
        return getServletRequest().getHeader(ACCESS_TOKEN);
    }

    public static HttpServletRequest getServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getServletResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    public static String getUserName() {
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        if (requestAttribute == null) {
            return "";
        }
        String userName = Optional.ofNullable(requestAttribute.getAttribute("userName", 0))
                .map(Object::toString)
                .orElse("");
        return userName;
    }

    public static String getMessage() {
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        if (requestAttribute == null) {
            return "";
        }
        String message = Optional.ofNullable(requestAttribute.getAttribute("message", 0))
                .map(Object::toString)
                .orElse("");
        return message;
    }
}