package com.example.server.model.vo;

public class LexicalVO {
    private String formal;
    private Object loc_details;
    private String item;
    private String pos;
    private String ne;
    private Object basic_words;
    private int byte_length;
    private int byte_offset;
    private String uri;

    public String getFormal() {
        return formal;
    }

    public void setFormal(String formal) {
        this.formal = formal;
    }

    public Object getLoc_details() {
        return loc_details;
    }

    public void setLoc_details(Object loc_details) {
        this.loc_details = loc_details;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public Object getBasic_words() {
        return basic_words;
    }

    public void setBasic_words(Object basic_words) {
        this.basic_words = basic_words;
    }

    public int getByte_length() {
        return byte_length;
    }

    public void setByte_length(int byte_length) {
        this.byte_length = byte_length;
    }

    public int getByte_offset() {
        return byte_offset;
    }

    public void setByte_offset(int byte_offset) {
        this.byte_offset = byte_offset;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
