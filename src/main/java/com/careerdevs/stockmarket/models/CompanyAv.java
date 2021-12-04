package com.careerdevs.stockmarket.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyAv {
    private String symbol;
    private String assetType;
    private String name;
    private String description;
    private String address;
    private String exchange;
    private String marketCap;
    private String divDate;

    public CompanyAv() {

    }
    public CompanyAv(String name, String symbol, String assetType, String description, String address) {
        this.name = name;
        this.symbol = symbol;
        this.assetType = assetType;
        this.description = description;
        this.address = address;
    }

    public String getDivDate() {
        return divDate;
    }

    @JsonProperty("DividendDate")
    public void setDivDate(String divDate) {
        this.divDate = divDate;
    }

    public String getMarketCap() {
        return marketCap;
    }

    @JsonProperty("MarketCapitalization")
    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

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
            int result = 0;
            try {
                result = a.getSymbol().compareTo(b.getSymbol());
            }
            catch (NullPointerException npe) {
                result = 0;
            }
            return result;
        }
    }
}
