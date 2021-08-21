package com.example.server.service;

import com.example.server.model.vo.JsonResult;

public interface IBodyService {
    JsonResult getKeysOfBody(byte[] image);

    JsonResult getCharacteristicsOfBody(byte[] image, String tyle);

    JsonResult getNumbersOfBody(byte[] image);

    JsonResult distinguishGesture(byte[] image);
}
