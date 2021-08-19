package com.example.server.model.vo;


public class TalkRequest {
    private static final String version = "3.0";
    private static final String service_id = "S56735";
    private static final String session_id = "";
    private String log_id;
    private Request request;

    public static String getVersion() {
        return version;
    }

    public static String getService_id() {
        return service_id;
    }

    public static String getSession_id() {
        return session_id;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "TalkRequest{" +
                "version='" + version + '\'' +
                ", service_id='" + service_id + '\'' +
                ", session_id='" + session_id + '\'' +
                ", log_id='" + log_id + '\'' +
                ", request=" + request +
                '}';
    }
}
