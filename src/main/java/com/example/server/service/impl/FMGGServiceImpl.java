package com.example.server.service.impl;

import com.example.server.model.vo.JsonResult;
import com.example.server.service.IFMGGService;
import com.example.server.utils.HttpUtils;
import com.example.server.utils.JsonUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FMGGServiceImpl implements IFMGGService {
    @Override
    public JsonResult classifyDrink(String image) throws Exception {
        String accessToken = this.getAuth("m3E6uK7fcLG38HdYaOsPA98c", "QmB6gAiSKtvx1u7qF2haxg0k6TISGOar");
        String url = "https://aip.baidubce.com/rpc/2.0/easydl/v1/retail/drink";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("image", image);
        String param = JsonUtils.objectToJson(paramsMap);
        String res = HttpUtils.post(url, accessToken,"application/json", param);
        Map result = (Map) JsonUtils.jsonToObject(res, Object.class);
        return JsonResult.success(result.get("results"));
    }


    public String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

}

