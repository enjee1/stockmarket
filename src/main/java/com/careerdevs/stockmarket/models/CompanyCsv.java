package com.careerdevs.stockmarket.models;

import java.util.Comparator;

public class CompanyCsv {

    private String symbol;
    private String name;
    private String exchange;
    private String assetType;
    private String ipoDate;
    private String delistingDate;
    private String status;

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    public String getAssetType() {
        return assetType;
    }
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    public String getIpoDate() {
        return ipoDate;
    }
    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate;
    }
    public String getDelistingDate() {
        return delistingDate;
    }
    public void setDelistingDate(String delistingDate) {
        this.delistingDate = delistingDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "{" + name + "::" + symbol + "::" + ipoDate + "::" + status + "}";
    }

    public static class SortBySymbol implements Comparator<CompanyCsv> {
        public int compare(CompanyCsv a, CompanyCsv b) {
            return a.getSymbol().compareTo(b.getSymbol());
        }
    }
}