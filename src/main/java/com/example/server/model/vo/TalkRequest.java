package com.example.server.model.vo;

import java.util.List;

public class TalkRequest {
    private String version;

    private String service_id;

    private String session_id = "";

    private String log_id;

    private Request request;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
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
