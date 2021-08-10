package com.example.demo.service;


import com.example.demo.model.vo.JsonResult;
import com.example.demo.model.vo.ResultVO;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/8/10 13:47
 */

public interface IImageService {

    public JsonResult<ResultVO> classifyGeneralImage(byte[] filepath);
}
