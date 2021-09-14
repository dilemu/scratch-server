package com.example.server.model.eum;

public enum NeEnum {
    n("n", "普通名词"), f("f", "方位名词"), s("s", "处所名词"), t("t", "时间名词"),
    nr("nr", "人名"), ns("ns", "地名"), nt("nt", "机构团体名"), nw("nw", "作品名"),
    nz("nz", "其他专名"), v("v", "普通动词"), vd("vd", "动副词"), vn("vn", "名动词"),
    a("a", "形容词"), ad("ad", "副形词"), d("d", "副词"), an("an", "名形词"),
    m("m", "数量词"), q("q", "量词"), r("r", "代词"), p("p", "介词"),
    c("c", "连词"), u("u", "助词"), xc("xc", "其他虚词"), w("w", "标点符号"),
    PER("PER", "人名"), LOC("LOC", "地名"), ORG("ORG", "机构名"), TIME("TIME", "时间");

    private String key;
    private String value;

    NeEnum() {
    }

    NeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        for (NeEnum neEnum : NeEnum.values()) {
            if (neEnum.getKey().equals(key)) {
                return neEnum.getValue();
            }
        }
        return null;
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
