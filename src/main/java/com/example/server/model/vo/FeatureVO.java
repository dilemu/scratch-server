package com.example.server.model.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <功能描述>
 *
 * @author 图像增强与特效返回结果
 * @date 2021/8/11 16:30
 */
public class FeatureVO {
    private long log_id;

    private String image;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
