package com.example.demo.controller;

import com.example.demo.model.vo.JsonResult;
import com.example.demo.model.vo.ResultVO;
import com.example.demo.service.IImageService;
import com.example.demo.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/7/8 16:23
 */
@Api("图像识别")
@RestController
@RequestMapping("/image")
public class ImageController {

    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/general/classify")
    @ApiOperation(value = "通用物品识别")
    private JsonResult<ResultVO> identifyGeneral(@RequestParam("file") MultipartFile file) throws IOException {
        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyGeneralImage(image);
    }
}
