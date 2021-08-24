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
     *
     * @param filepath
     * @return
     */
    public JsonResult<ImageVO> classifyGeneralImage(byte[] filepath);

    /**
     * 图像风格转换
     *
     * @param image
     * @param style
     * @return
     */
    JsonResult<FeatureVO> convertStyle(byte[] image, String style);

    /**
     * 动漫人像
     *
     * @param image
     * @return
     */
    JsonResult<FeatureVO> convertAnime(byte[] image);

    /**
     * 动物识别
     *
     * @param
     * @return
     */
    JsonResult<ImageVO> classifyAnimal(byte[] image);

    /**
     * 植物识别
     * @param image
     * @return
     */
    JsonResult<ImageVO> classifyPlant(byte[] image);

    /**
     * 果蔬识别
     * @param image
     * @return
     */
    JsonResult<ImageVO> classifyIngredient(byte[] image);

    /**
     * 地标识别
     * @param image
     * @return
     */
    JsonResult classifyLandmark(byte[] image);

    /**
     * 货币识别
     * @param image
     * @return
     */
    JsonResult<CurrencyVO> classifyCurrency(byte[] image);

    /**
     * logo识别
     * @param image
     * @return
     */
    JsonResult classifyLogo(byte[] image);

    /**
     * 菜品识别
     * @param image
     * @return
     */
    JsonResult classifyDish(byte[] image);
}
