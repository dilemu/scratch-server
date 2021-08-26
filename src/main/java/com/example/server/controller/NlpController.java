package com.example.server.controller;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NlpRequest;
import com.example.server.service.INlpService;
import com.example.server.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("自然语言处理")
@RequestMapping("/nlp")
public class NlpController {
    private final INlpService NlpService;

    @Autowired
    public NlpController(INlpService nlpService) {
        NlpService = nlpService;
    }

    @ApiOperation("词法分析")
    @PostMapping("/lexical/analysis")
    private JsonResult analysisLexical(@RequestBody NlpRequest nlpRequest) {
        if (StringUtils.isEmpty(nlpRequest.getText())) {
            throw new BizBaseException("文本不可为空");
        }

        return NlpService.analysisLexical(nlpRequest);
    }

    @ApiOperation("情感倾向分析")
    @PostMapping("/affective/tendency/analysis")
    private JsonResult analysisAffectiveTendency(@RequestBody NlpRequest nlpRequest) {
        if (StringUtils.isEmpty(nlpRequest.getText())) {
            throw new BizBaseException("文本不可为空");
        }

        return NlpService.analysisAffectiveTendency(nlpRequest);
    }

    @ApiOperation("词义相似度")
    @PostMapping("/semantic/similarity")
    private JsonResult analysisSemanticSimilarity(@RequestBody NlpRequest nlpRequest) {
        if (StringUtils.isAnyEmpty(nlpRequest.getText(), nlpRequest.getText_2())) {
            throw new BizBaseException("文本不可为空");
        }

        return NlpService.analysisSemanticSimilarity(nlpRequest);
    }

    @ApiOperation("文本纠错")
    @PostMapping("/text/recognition")
    private JsonResult recoverErrOfText(@RequestBody NlpRequest nlpRequest) {
        if (StringUtils.isEmpty(nlpRequest.getText())) {
            throw new BizBaseException("文本不可为空");
        }

        return NlpService.recoverErrOfText(nlpRequest);
    }

    @ApiOperation("地址识别")
    @PostMapping("/address/identification")
    private JsonResult classifyAddress(@RequestBody NlpRequest nlpRequest) {
        if (StringUtils.isEmpty(nlpRequest.getText())) {
            throw new BizBaseException("文本不可为空");
        }

        return NlpService.classifyAddress(nlpRequest);
    }

    @ApiOperation("汉语检索")
    @PostMapping("/Chinese/retrieval")
    private JsonResult retrievalChinese(@RequestBody NlpRequest nlpRequest) throws Exception {
        if (StringUtils.isEmpty(nlpRequest.getText())) {
            throw new BizBaseException("文本不可为空");
        }

        return NlpService.retrievalChinese(nlpRequest);
    }
}
