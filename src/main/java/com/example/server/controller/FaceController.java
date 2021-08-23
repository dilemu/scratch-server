package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFaceService;
import com.example.server.utils.Base64Util;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api("人脸识别")
@RequestMapping("/face")
public class FaceController {

    private final IFaceService faceService;

    @Autowired
    public FaceController(IFaceService faceService) {
        this.faceService = faceService;
    }

    @PostMapping("/emotion/classify")
    @ApiOperation("情绪识别")
    private JsonResult verifyFace(@RequestParam("file") MultipartFile file) throws IOException {
        if (StringUtils.isEmpty(file.getOriginalFilename())) {
            throw new BizBaseException("文件不能为空");
        }

        String image = Base64Util.encode(file.getBytes());

        return faceService.classifyFace(image);
    }
}
