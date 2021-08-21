package com.example.server.model.eum;

public enum Gesture {
    One("数字1（原食指", 1), Five("数字5（原掌心向前）", 2), Fist("拳头", 3), OK("OK", 4),
    Prayer("祈祷", 5), Congratulation("作揖", 6), Honour("作别", 7), Heart_single("单手比心", 8),
    Thumb_up("点赞", 9), Thumb_down("Diss", 10), ILY("Rock", 11), Palm_up("掌心向上", 12),
    Heart_1("双手比心1", 13), Heart_2("双手比心2", 14), Heart_3("双手比心3", 15), Two("数字2", 16),
    Three("数字3", 17), Four("数字4", 18), Six("数字6", 19), Seven("数字7", 20),
    Eight("数字8", 21), Nine("数字9", 22), Rock("Rock", 23), Insult	("竖中指", 24);

    private String name;
    private int index;

    Gesture(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
