package com.careerdevs.stockmarket.utilties;

import com.careerdevs.stockmarket.models.CompanyAv;
import com.careerdevs.stockmarket.models.CompanyCsv;

public class FeatureFactory {

    public static CompanyCsv feature1(CompanyCsv c) {
        CompanyCsv company = new CompanyCsv();
        company.setName(c.getName());
        company.setSymbol(c.getSymbol());
        company.setExchange(c.getExchange());

        return company;
    }

    public static CompanyCsv feature2(CompanyCsv c) {
        CompanyCsv company = new CompanyCsv();
        company.setName(c.getName());
        company.setSymbol(c.getSymbol());
        company.setIpoDate(c.getIpoDate());

        return company;
    }

    public static CompanyCsv feature3And4(CompanyCsv c) {
        CompanyCsv company = new CompanyCsv();
        company.setName(c.getName());
        company.setSymbol(c.getSymbol());
        company.setExchange(c.getExchange());

        return company;
    }

    public static CompanyAv feature5(CompanyAv c) {
        CompanyAv company = new CompanyAv();
        company.setName(c.getName());
        company.setSymbol(c.getSymbol());
        company.setAssetType(c.getAssetType());
        company.setDescription(c.getDescription());
        company.setAddress(c.getAddress());

        return company;
    }

    public static CompanyAv feature6(CompanyAv c) {
        CompanyAv company = new CompanyAv();
        company.setName(c.getName());
        company.setSymbol(c.getSymbol());
        company.setMarketCap(c.getMarketCap());

        return company;
    }

    public static CompanyAv feature7(CompanyAv c) {
        CompanyAv company = new CompanyAv();
        company.setName(c.getName());
        company.setSymbol(c.getSymbol());
        company.setDivDate(c.getDivDate());

        return company;
    }

}
