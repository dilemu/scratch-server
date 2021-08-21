package com.example.server.controller;

import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

@RestController
@Api("人脸识别")
@RequestMapping("/face")
public class FaceController {

//    private final IFaceService faceService;
//
//    @Autowired
//    public FaceController(IFaceService faceService) {
//        this.faceService = faceService;
//    }

    @PostMapping("/classify")
    @ApiOperation("人脸识别")
    private JsonResult verifyFace(){
        return null;
    }
}
