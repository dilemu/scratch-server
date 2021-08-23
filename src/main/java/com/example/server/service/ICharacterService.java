package com.example.server.service;

import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UniversalRequest;

public interface ICharacterService {

    JsonResult<CharacterResult> classifyLicensePlate(byte[] image, String multi_detect);

//    JsonResult<CharacterResult> classifyNormalCharacter(byte[] image, UniversalRequest universalRequest);

    JsonResult<CharacterResult> classifyQRCode(byte[] image);

    JsonResult<CharacterResult> classifyHandwritten(byte[] image, UniversalRequest universalRequest);
}
