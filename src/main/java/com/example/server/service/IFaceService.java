package com.example.server.service;

import com.example.server.model.vo.JsonResult;

public interface IFaceService {
    JsonResult classifyFace(String image);
}
