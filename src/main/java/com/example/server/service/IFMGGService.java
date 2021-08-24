package com.example.server.service;

import com.example.server.model.vo.JsonResult;

public interface IFMGGService {
    JsonResult classifyDrink(String image) throws Exception;
}
