package com.example.server.model.eum;

public enum WeatherErrorCode {
    one(204, "请求成功，但你查询的地区暂时没有你需要的数据。"),
    two(400, "请求错误，可能包含错误的请求参数或缺少必选的请求参数。"),
    three(401, "认证失败，可能使用了错误的KEY、数字签名错误、KEY的类型错误（如使用SDK的KEY去访问Web API）。"),
    four(402, "超过访问次数或余额不足以支持继续访问服务，你可以充值、升级访问量或等待访问量重置。"),
    five(403, "无访问权限，可能是绑定的PackageName、BundleID、域名IP地址不一致，或者是需要额外付费的数据。"),
    six(404, "查询的数据或地区不存在。"),
    seven(429, "超过限定的QPM（每分钟访问次数），请参考QPM说明"),
    eight(500, "无响应或超时");

    private int code;
    private String msg;

    WeatherErrorCode() {
    }

    WeatherErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static WeatherErrorCode getError(Integer code) {
        for (WeatherErrorCode weatherErrorCode : WeatherErrorCode.values()) {
            if (weatherErrorCode.getCode() == code) {
                return weatherErrorCode;
            }
        }
        return null;
    }
}
