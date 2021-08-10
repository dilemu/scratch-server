package com.example.demo.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/8/10 14:04
 */
public class AIUtils {
    private static final String IMAGE_APP_ID = "24667544";
    private static final String IMAGE_API_KEY = "Ewv3X3GTIG3FMUAv8c0En1Za";
    private static final String IMAGE_SECRET_KEY = "SMldQouVe1Z5iO03Ezo7TZPE9EdyvAS4";

    public static AipImageClassify getImageClient(){
        AipImageClassify client = new AipImageClassify(IMAGE_APP_ID, IMAGE_API_KEY, IMAGE_SECRET_KEY);
        return client;
    }
}
