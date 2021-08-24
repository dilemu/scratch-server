package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFMGGService;
import com.example.server.utils.Base64Util;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api("快消识别")
@RequestMapping("/fmgg")
public class FMGGController {
    private final IFMGGService FMGGService;

    @Autowired
    public FMGGController(IFMGGService fmggService) {
        FMGGService = fmggService;
    }

    @ApiOperation("饮品识别")
    @PostMapping("/drink/classify")
    private JsonResult verifyDrink(@RequestParam("file") MultipartFile file) throws Exception {
        if(StringUtils.isEmpty(file.getOriginalFilename())){
            throw new BizBaseException("文件不可为空");
        }

        String image = Base64Util.encode(file.getBytes());
        return FMGGService.classifyDrink(image);
    }
}
