package com.example.server.utils;

import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;

/**
 * description: AuthIdUtils
 * date: 2020/9/2 10:53
 *
 * @author:
 */
public class AuthIdUtils {
    private AuthIdUtils() {
    }

    public static String generateAuthId(List<String> keyList) {
        // 1.排序
        Collections.sort(keyList);
        // 2.生成字符串
        String keys = String.join("", keyList);
        // 3.md5生成authId
        return MD5Utils.string2MD5(keys);
    }

    public static String generateResourceId(List<String> propList) {
        // 1.排序
        Collections.sort(propList);
        // 2.md5生成resourceId
        return MD5Utils.string2MD5(String.join("", String.join("", propList)));
    }

    /**
     * authid-MD5生成
     *
     * @param departmentId
     * @param positionId
     * @return
     */
    public static String createAuthId(String departmentId, String positionId) {
        StringBuilder sb = new StringBuilder();
        return DigestUtils.md5DigestAsHex(sb.append(departmentId).append(positionId).toString().getBytes());
    }
}
