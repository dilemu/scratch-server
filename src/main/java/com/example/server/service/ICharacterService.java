package com.example.server.service;

import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.UniversalRequest;

public interface ICharacterService {

    CharacterResult<Object> classifyLicensePlate(byte[] image, String multi_detect);

    CharacterResult classifyNormalCharacter(byte[] image, UniversalRequest universalRequest);

    CharacterResult classifyQRCode(byte[] image);

    CharacterResult classifyHandwritten(byte[] image, UniversalRequest universalRequest);
}
