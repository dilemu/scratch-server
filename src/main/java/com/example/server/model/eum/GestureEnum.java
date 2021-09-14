package com.example.server.model.eum;

public enum GestureEnum {
    One("One", "数字1（原食指）"), Five("Five", "数字5（原掌心向前）"), Fist("Fist", "拳头"), Ok("Ok", "OK"),
    Prayer("Prayer", "祈祷"), Congratulation("Congratulation", "作揖"), Honour("Honour", "作别"), Heart_single("Heart_single", "单手比心"),
    Thumb_up("Thumb_up", "点赞"), Thumb_down("Thumb_down", "Diss"), ILY("ILY", "Rock"), Palm_up("Palm_up", "掌心向上"),
    Heart_1("Heart_1", "双手比心1"), Heart_2("Heart_2", "双手比心2"), Heart_3("Heart_3", "双手比心3"), Two("Two", "数字2"),
    Three("Three", "数字3"), Four("Four", "数字4"), Six("Six", "数字6"), Seven("Seven", "数字7"),
    Eight("Eight", "数字8"), Nine("Nine", "数字9"), Rock("Rock", "Rock"), Insult("Insult", "竖中指");

    private String key;
    private String value;

    GestureEnum(String key, String value) {
        this.key = key;
        this.value = value;
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
