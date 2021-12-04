package com.careerdevs.stockmarket.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

public class CompanyAv {
    private String symbol;
    private String assetType;
    private String name;
    private String description;
    private String address;
    private String exchange;

    public String getExchange() {
        return exchange;
    }

    @JsonProperty("Exchange")
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetType() {
        return assetType;
    }

    @JsonProperty("AssetType")
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    public static class SortBySymbol implements Comparator<CompanyAv> {
        public int compare(CompanyAv a, CompanyAv b) {
            return a.getSymbol().compareTo(b.getSymbol());
        }
    }
}
