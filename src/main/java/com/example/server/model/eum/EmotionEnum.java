package com.example.server.model.eum;

public enum EmotionEnum {
    happy("happy", "开心"),
    angry("angry", "愤怒"),
    disgust("disgust", "厌恶"),
    fear("fear", "恐惧"),
    sad("sad", "伤心"),
    surprise("surprise", "惊讶"),
    neutral("neutral", "无情绪");

    private String key;
    private String value;

    EmotionEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    EmotionEnum() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
