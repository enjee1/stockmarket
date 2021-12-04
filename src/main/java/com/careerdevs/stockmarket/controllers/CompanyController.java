package com.careerdevs.stockmarket.controllers;

import com.careerdevs.stockmarket.models.CompanyAv;
import com.careerdevs.stockmarket.models.CompanyCsv;
import com.careerdevs.stockmarket.parsers.StockCsvParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/stock")
public class CompanyController {

    @Autowired
    private Environment env;
    private final static String AV_URL = "https://www.alphavantage.co/query";

    @GetMapping("/allcsvdata")
    public List<CompanyCsv> getAllCsvData(RestTemplate restTemplate) {
        List<CompanyCsv> allCsvData = StockCsvParser.readCSV();

        return allCsvData;

    }
    @GetMapping("/feature1")
    // all companies name, symbol, and exchange (in alphabetical order by symbol)
    public List<CompanyCsv> getNameSymExchData(RestTemplate restTemplate) {
        List<CompanyCsv> allCsvData = StockCsvParser.readCSV();
        List<CompanyCsv> filteredCsvData = new ArrayList<>();

        for (CompanyCsv c : allCsvData) {
            CompanyCsv company = new CompanyCsv(c.getSymbol(), c.getName(), c.getExchange());
            filteredCsvData.add(company);
        }

        Collections.sort(filteredCsvData, new CompanyCsv.SortBySymbol());

        return filteredCsvData;
    }


    /*
    GET all companies overview information. Must include data from the following keys
        Symbol, AssetType, Name, Description, Address
     */
    @GetMapping("/feature5")
    public List<CompanyAv> getAvOverview(RestTemplate restTemplate) {

        String ovUrl = AV_URL + "?function=OVERVIEW&symbol=";
        List<CompanyCsv> csvData = StockCsvParser.readCSV();
        List<CompanyAv> allCompData = new ArrayList<>();

        for (CompanyCsv comp : csvData) {
              String tempUrl = ovUrl + comp.getSymbol() + "&apikey=" + env.getProperty("av.key");
              CompanyAv compApiData = restTemplate.getForObject(tempUrl, CompanyAv.class);
              CompanyAv company = new CompanyAv
                      (compApiData.getName(), compApiData.getSymbol(), compApiData.getAssetType(), compApiData.getDescription(), compApiData.getAddress());
              allCompData.add(company);
        }

        Collections.sort(allCompData, new CompanyAv.SortBySymbol());
        return allCompData;
    }

}
