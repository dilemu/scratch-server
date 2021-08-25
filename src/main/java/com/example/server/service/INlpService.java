package com.example.server.service;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NlpRequest;

public interface INlpService {
    JsonResult analysisLexical(NlpRequest nlpRequest);

    JsonResult analysisAffectiveTendency(NlpRequest nlpRequest);

    JsonResult analysisSemanticSimilarity(NlpRequest nlpRequest);

    JsonResult recoverErrOfText(NlpRequest nlpRequest);

    JsonResult classifyAddress(NlpRequest nlpRequest);
}
