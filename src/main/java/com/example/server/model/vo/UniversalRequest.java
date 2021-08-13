package com.example.server.model.vo;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/12 17:14
 */
public class UniversalRequest {

    private String language_type = "CHN_ENG";
    private String probability = "false";
    private String detect_language = "false";
    private String detect_direction = "false";
    private String recognize_granularity = "false";
    private String multi_detect = "false";

    public String getLanguage_type() {
        return language_type;
    }

    public void setLanguage_type(String language_type) {
        this.language_type = language_type;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getDetect_language() {
        return detect_language;
    }

    public void setDetect_language(String detect_language) {
        this.detect_language = detect_language;
    }

    public String getDetect_direction() {
        return detect_direction;
    }

    public void setDetect_direction(String detect_direction) {
        this.detect_direction = detect_direction;
    }

    public String getRecognize_granularity() {
        return recognize_granularity;
    }

    public void setRecognize_granularity(String recognize_granularity) {
        this.recognize_granularity = recognize_granularity;
    }

    public String getMulti_detect() {
        return multi_detect;
    }

    public void setMulti_detect(String multi_detect) {
        this.multi_detect = multi_detect;
    }
}
