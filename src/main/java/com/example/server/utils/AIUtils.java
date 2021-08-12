package com.example.server.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.imageprocess.AipImageProcess;
import com.baidu.aip.ocr.AipOcr;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/10 14:04
 */
public class AIUtils {


    public static AipImageClassify getImageClient(){
        AipImageClassify client = new AipImageClassify("24667544", "Ewv3X3GTIG3FMUAv8c0En1Za", "SMldQouVe1Z5iO03Ezo7TZPE9EdyvAS4");
        return client;
    }

    public static AipImageProcess getImageProcessClient(){
        AipImageProcess client = new AipImageProcess("24680995", "KbSfM3HcTAAVZDqWI2zBU7jh", "2GwSwIPfrcw3pRGReNVIr9UuvMGGl4UG");
        return client;
    }

    public static AipOcr getOcrClient(){
        AipOcr client = new AipOcr("24689350", "aAeFDFH8dkMYfGrHjdWVbxbE", "8tA3XoRA4DwUWzldEFHPsbwpSkNOZZWe");
        return client;
    }
}
