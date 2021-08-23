package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.SynthesisRequest;
import com.example.server.service.IVoiceService;
import com.example.server.utils.StringUtils;
import com.example.server.utils.UserContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/19 13:34
 */
@RestController
@Api("语音")
@RequestMapping("/voice")
public class VoiceController {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoiceController.class);

    private final IVoiceService voiceService;

    @Autowired
    public VoiceController(IVoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @PostMapping("/classify")
    @ApiOperation(value = "语音识别")
    private JsonResult identifyVoice(@RequestParam("file") MultipartFile file, String format, int rate) throws IOException {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException("文件不能为空");
        }

        byte[] data = file.getBytes();
        return voiceService.classifyVoice(data, format, rate);
    }

    @RequestMapping(value = {"/speech/synthesis"}, method = {RequestMethod.POST}, produces = "audio/wav")
//    @PostMapping("/speech/synthesis")
    @ApiOperation(value = "语音合成")
    private byte[] syntheticSpeech(@RequestBody SynthesisRequest synthesisRequest) throws IOException {
        int maxLength = 1024;
        if (synthesisRequest.getStr().length() >= maxLength) {
            throw new BizBaseException("文本长度过长");
        }

        return voiceService.syntheticSpeech(synthesisRequest);
    }
}
