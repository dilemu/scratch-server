package com.example.server.service;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.model.vo.UserResult;

public interface IUserService {

    JsonResult login(UserRequest requestVO);

    JsonResult getUserInfo(String token) throws Exception;

    JsonResult verifyTimes(String token) throws Exception;
}
