package com.example.server.service;


import com.example.server.model.vo.FeatureVO;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NormalImageVO;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/10 13:47
 */

public interface IImageService {

    /**
     * 通用识别
     * @param filepath
     * @return
     */
    public JsonResult<NormalImageVO> classifyGeneralImage(byte[] filepath);

    /**
     * 图像风格转换
     * @param image
     * @param style
     * @return
     */
    JsonResult<FeatureVO> convertStyle(byte[] image, String style);
}
