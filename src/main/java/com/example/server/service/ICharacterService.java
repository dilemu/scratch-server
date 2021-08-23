package com.example.server.service;

import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UniversalRequest;

public interface ICharacterService {

    JsonResult classifyLicensePlate(byte[] image, String multi_detect);

    JsonResult classifyNormalCharacter(byte[] image, UniversalRequest universalRequest);

    JsonResult classifyQRCode(byte[] image);

    JsonResult classifyHandwritten(byte[] image, UniversalRequest universalRequest);
}
