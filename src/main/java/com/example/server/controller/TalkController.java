package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.*;
import com.example.server.service.ITalkService;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("智能对话")
@RestController
@RequestMapping("/talk")
public class TalkController {
    private final ITalkService talkService;

    @Autowired
    public TalkController(ITalkService talkService) {
        this.talkService = talkService;
    }

    @PostMapping("/classify")
    @ApiOperation(value = "智能对话")
    private JsonResult utterance(@RequestBody Request request) throws Exception {
        if (StringUtils.isEmpty(request.getTerminal_id())) {
            request.setTerminal_id("000");
        }

        if (StringUtils.isEmpty(request.getQuery())) {
            throw new BizBaseException("query can not be null");
        }

        return talkService.getResponse(request);
    }
}