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
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class CompanyController {

    //TODO: Add feature_5_code to CompanyController
    @Autowired
    private Environment env;
    private final static String AV_URL = "https://www.alphavantage.co/query";

    @GetMapping("/alldata")
    public List<CompanyCsv> getAllData(RestTemplate restTemplate) {
        List<CompanyCsv> unsortedData = StockCsvParser.readCSV();
        return unsortedData;

    }

    @GetMapping("/overview")
    public CompanyAv getAvOverview(RestTemplate restTemplate,
                                @RequestParam String function,
                                @RequestParam String symbol) {

        String tempUrl = AV_URL + "?function=" + function + "&symbol=" + symbol + "&apikey=" + env.getProperty("av.key");

        return restTemplate.getForObject(tempUrl, CompanyAv.class);

    }

}
