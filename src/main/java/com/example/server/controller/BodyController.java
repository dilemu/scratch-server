package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.CharacterRequest;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IBodyService;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api("人体识别")
@RequestMapping("/body")
public class BodyController {

    private final IBodyService bodyService;

    public BodyController(IBodyService bodyService) {
        this.bodyService = bodyService;
    }

    @PostMapping("/keys")
    @ApiOperation("人体关键点")
    private JsonResult getKeysOfBody(@RequestParam("file") MultipartFile file) throws IOException {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return bodyService.getKeysOfBody(image);
    }

    @PostMapping("/characteristics")
    @ApiOperation("人体特征")
    private JsonResult getCharacteristicsOfBody(@RequestParam("file") MultipartFile file, String type) throws IOException {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return bodyService.getCharacteristicsOfBody(image, type);
    }

    @PostMapping("/numbers")
    @ApiOperation("人流量")
    private JsonResult getNumbersOfBody(@RequestParam("file") MultipartFile file) throws IOException {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return bodyService.getNumbersOfBody(image);
    }

    @PostMapping("/gesture/recognition")
    @ApiOperation("手势识别")
    private JsonResult classifyGesture(@RequestParam("file") MultipartFile file) throws IOException {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return bodyService.distinguishGesture(image);
    }
}
