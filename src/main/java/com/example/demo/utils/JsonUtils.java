package com.example.demo.utils;

import com.google.gson.Gson;

/**
 * <功能描述>
 *
 * @author
 * @date 2020/10/2 21:47
 */
public class JsonUtils {
    private JsonUtils() {
    }

    private static final Gson gson = new Gson();

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static String objectToJson(Object obj) {
        return gson.toJson(obj);
    }
}
