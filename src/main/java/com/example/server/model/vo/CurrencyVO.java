package com.example.server.model.vo;


/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/18 14:57
 */
public class CurrencyVO {
    private String currencyName;
    private String year;
    private String hasdetail;
    private String currencyCode;
    private String currencyDenomination;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHasdetail() {
        return hasdetail;
    }

    public void setHasdetail(String hasdetail) {
        this.hasdetail = hasdetail;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyDenomination() {
        return currencyDenomination;
    }

    public void setCurrencyDenomination(String currencyDenomination) {
        this.currencyDenomination = currencyDenomination;
    }
}
