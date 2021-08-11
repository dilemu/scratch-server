package com.example.server.model.vo;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/7/8 17:33
 */
public class NormalImageVO {
    private String type;

    private double score;

    private String root;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
