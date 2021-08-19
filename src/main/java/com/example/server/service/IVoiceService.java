package com.example.server.service;

import com.example.server.model.vo.JsonResult;

import java.io.IOException;

public interface IVoiceService {
    JsonResult classifyVoice(byte[] data, String format, int rate);

    byte[] syntheticSpeech(String str) throws IOException;
}
