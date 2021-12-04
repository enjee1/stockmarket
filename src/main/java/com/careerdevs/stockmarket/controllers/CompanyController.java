package com.careerdevs.stockmarket.controllers;

import com.careerdevs.stockmarket.models.CompanyAv;
import com.careerdevs.stockmarket.models.CompanyCsv;
import com.careerdevs.stockmarket.parsers.StockCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        Collections.sort(allCsvData, new CompanyCsv.SortBySymbol());

        return allCsvData;
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

        for (CompanyCsv company : csvData) {
              String tempUrl = ovUrl + company.getSymbol() + "&apikey=" + env.getProperty("av.key");
              CompanyAv compApiData = restTemplate.getForObject(tempUrl, CompanyAv.class);
              allCompData.add(compApiData);
        }

        return allCompData;
    }

}
