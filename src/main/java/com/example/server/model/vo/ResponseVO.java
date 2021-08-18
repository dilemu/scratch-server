package com.example.server.model.vo;

import java.util.List;

public class ResponseVO<T> {
        private int status;
        private String msg;
        private String origin;
        private T schema;
        private T actions;
        private String raw_query;
        private T slot_history;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public T getSchema() {
        return schema;
    }

    public void setSchema(T schema) {
        this.schema = schema;
    }

    public T getActions() {
        return actions;
    }

    public void setActions(T actions) {
        this.actions = actions;
    }


    public T getSlot_history() {
        return slot_history;
    }

    public void setSlot_history(T slot_history) {
        this.slot_history = slot_history;
    }

    public String getRaw_query() {
        return raw_query;
    }

    public void setRaw_query(String raw_query) {
        this.raw_query = raw_query;
    }
}
