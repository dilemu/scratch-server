package com.example.server.service;


import com.example.server.model.vo.*;

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
    public JsonResult<ImageVO> classifyGeneralImage(byte[] filepath);

    /**
     * 图像风格转换
     * @param image
     * @param style
     * @return
     */
    JsonResult<FeatureVO> convertStyle(byte[] image, String style);

    /**
     * 动漫人像
     * @param image
     * @return
     */
    JsonResult<FeatureVO> convertAnime(byte[] image);

    JsonResult<ImageVO> classifyAnimal(byte[] image);

    JsonResult<ImageVO> classifyPlant(byte[] image);

    JsonResult<ImageVO> classifyIngredient(byte[] image);

    JsonResult classifyLandmark(byte[] image);

    JsonResult<CurrencyVO> classifyCurrency(byte[] image);
}
