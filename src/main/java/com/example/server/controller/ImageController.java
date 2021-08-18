package com.example.server.controller;

import com.example.server.model.vo.FeatureVO;
import com.example.server.model.vo.ImageResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NormalImageVO;
import com.example.server.service.IImageService;
import com.example.server.service.IUserService;
import com.example.server.utils.UserContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * <功能描述>
 *
 * @author
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
    private JsonResult<NormalImageVO> identifyGeneral(@RequestParam("file") MultipartFile file) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }
        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyGeneralImage(image);
    }

    @PostMapping("/animal/classify")
    @ApiOperation(value = "动物识别")
    private JsonResult<ImageResult> identifyAnimal(@RequestParam("file") MultipartFile file) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyAnimal(image);
    }

    @PostMapping("/plant/classify")
    @ApiOperation(value = "植物识别")
    private JsonResult<ImageResult> identifyPlant(@RequestParam("file") MultipartFile file) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyPlant(image);
    }

    @PostMapping("/ingredient/classify")
    @ApiOperation(value = "果蔬识别")
    private JsonResult<ImageResult> identifyIngredient(@RequestParam("file") MultipartFile file) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyIngredient(image);
    }

    @PostMapping("/landmark/classify")
    @ApiOperation(value = "地标识别")
    private JsonResult<ImageResult> identifyLandmark(@RequestParam("file") MultipartFile file) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyLandmark(image);
    }

    @PostMapping("/currency/classify")
    @ApiOperation(value = "货币识别")
    private JsonResult<ImageResult> identifyCurrency(@RequestParam("file") MultipartFile file) throws Exception {
        if(UserContextUtils.getMessage().equals("账号余额不足")){
            return JsonResult.error(401, "账号余额不足");
        }else if(UserContextUtils.getMessage().equals("token无效，请重新登录")){
            return JsonResult.error(402, "token无效，请重新登录");
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.classifyCurrency(image);
    }

    @PostMapping("/style/convert")
    @ApiOperation(value = "图像风格转换")
    private JsonResult<FeatureVO> convertStyle(@RequestParam("file") MultipartFile file, @RequestParam("style") String style) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.convertStyle(image, style);
    }

    @PostMapping("/cartoon/portrait")
    @ApiOperation(value = "动漫人像")
    private JsonResult<FeatureVO> convertAnime(@RequestParam("file") MultipartFile file) throws Exception {
        String status = UserContextUtils.getMessage();
        if(status.equals("账号余额不足")){
            return JsonResult.error(401, status);
        }else if(status.equals("token无效，请重新登录")){
            return JsonResult.error(402, status);
        }

        if (null == file) {
            return JsonResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return imageService.convertAnime(image);
    }
}
