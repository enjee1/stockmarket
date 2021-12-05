package com.careerdevs.stockmarket.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyCsv {

    private String symbol;
    private String name;
    private String exchange;
    private String assetType;
    private String ipoDate;
    private String delistingDate;
    private String status;

    public CompanyCsv() {}

    public CompanyCsv(String name, String ipoDate) {
        this.name = name;
        this.ipoDate = ipoDate;
    }

    public CompanyCsv(String symbol, String name, String exchange) {
        this.symbol = symbol;
        this.name = name;
        this.exchange = exchange;
    }

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
            int result;
            String compASymbol = a.getSymbol();
            String compBSymbol = b.getSymbol();

            if (compASymbol == null && compBSymbol != null) {
                result = 1;
            } else if (compASymbol != null && compBSymbol == null) {
                result = -1;
            } else if (compASymbol == null){
                result = 0;
            } else {
                result = compASymbol.compareTo(compBSymbol);
            }

            return result;
        }
    }

    public static class SortByIpoDate implements Comparator<CompanyCsv> {
        public int compare(CompanyCsv a, CompanyCsv b) {
            int result;
            String dateString1 = a.getIpoDate();
            String dateString2 = b.getIpoDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date date1 = null;
            try {
                date1 = format.parse(dateString1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date date2 = null;
            try {
                date2 = format.parse(dateString2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date1 == null && date2 != null) {
                result = 1;
            } else if (date1 != null && date2 == null) {
                result = -1;
            } else if (date1 == null) {
                result = 0;
            } else {
                result = date1.compareTo(date2);
            }

            return result;
        }
    }
}