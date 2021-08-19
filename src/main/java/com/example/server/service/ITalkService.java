package com.example.server.service;


import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.Request;

public interface ITalkService {

    JsonResult getResponse(Request request) throws Exception;
}
