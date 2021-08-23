package com.example.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <功能描述>
 * String 字符串相关工具类
 *
 * @author
 * @date 2020/10/19 10:44
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    public static Boolean isChinese(String str) {
        boolean result = false;
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            n = (int) str.charAt(i);
            if ((19968 <= n && n < 40869)) {
                result = true;
            }
        }

        return result;
    }
}
