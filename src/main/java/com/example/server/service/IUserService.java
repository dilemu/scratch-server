package com.example.server.service;

import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UserRequest;
import com.example.server.model.vo.UserResult;

public interface IUserService {

    UserResult login(UserRequest requestVO);

    UserResult getUserInfo(String token) throws Exception;

    UserResult verifyTimes(String token) throws Exception;
}
