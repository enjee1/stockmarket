package com.careerdevs.stockmarket.controllers;

import com.careerdevs.stockmarket.models.CompanyAv;
import com.careerdevs.stockmarket.models.CompanyCsv;
import com.careerdevs.stockmarket.utilties.StockComparator;
import com.careerdevs.stockmarket.utilties.StockCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/stock")
public class CompanyController {

    @Autowired
    private Environment env;

    private final static String AV_URL = "https://www.alphavantage.co/query";
    private final static List<CompanyCsv> ALL_CSV_DATA = StockCsvParser.readCSV();

    @GetMapping("/allcsvdata")
    // Uses local .csv file
    public List<CompanyCsv> getAllCsvData(RestTemplate restTemplate) {

        return ALL_CSV_DATA;

    }
    @GetMapping("/feature1")
    // Uses local .csv file
    // GET all companies name, symbol, and exchange (in alphabetical order by symbol)
    public List<CompanyCsv> getNameSymExchData(RestTemplate restTemplate) {
        List<CompanyCsv> filteredCsvData = new ArrayList<>();

        for (CompanyCsv c : ALL_CSV_DATA) {
            CompanyCsv company = new CompanyCsv(c.getSymbol(), c.getName(), c.getExchange());
            filteredCsvData.add(company);
        }

        Collections.sort(filteredCsvData, new StockComparator.SortCompCsvBySymbol());

        return filteredCsvData;
    }

    @GetMapping("/feature2")
    // Uses local .csv file
    // GET all companies name and IPO date (in ascending order by date)
    public List<CompanyCsv> getNameIpoData(RestTemplate restTemplate) {
        List<CompanyCsv> filteredCsvData = new ArrayList<>();

        for (CompanyCsv c : ALL_CSV_DATA) {
            CompanyCsv company = new CompanyCsv(c.getName(), c.getIpoDate());
            filteredCsvData.add(company);
        }

        Collections.sort(filteredCsvData, new StockComparator.SortCompCsvByIpoDate());

        return filteredCsvData;
    }

    @GetMapping("/feature3")
    // Uses local .csv file
    // GET all companies traded on the NASDAQ exchange
    public List<CompanyCsv> getNasdaqCompanies(RestTemplate restTemplate) {
        List<CompanyCsv> filteredCsvData = new ArrayList<>();

        for (CompanyCsv c : ALL_CSV_DATA) {
            if (c.getExchange().equals("NASDAQ")){
                filteredCsvData.add(c);
            }
        }
        return filteredCsvData;
    }

    @GetMapping("/feature4")
    // Uses local .csv file
    // GET all companies traded on the NYSE
    public List<CompanyCsv> getNyseCompanies(RestTemplate restTemplate) {
        List<CompanyCsv> filteredCsvData = new ArrayList<>();

        for (CompanyCsv c : ALL_CSV_DATA) {
            if (c.getExchange().equals("NYSE")){
                filteredCsvData.add(c);
            }
        }
        return filteredCsvData;
    }

    @GetMapping("/feature5")
    // Uses external API call
    /*
        GET all companies overview information. Include data from the following keys:
            Symbol, AssetType, Name, Description, Address
     */
    public List<CompanyAv> getAvOverview(RestTemplate restTemplate) {

        String ovUrl = AV_URL + "?function=OVERVIEW&symbol=";
        List<CompanyAv> allCompData = new ArrayList<>();

        for (CompanyCsv comp : ALL_CSV_DATA) {
              String tempUrl = ovUrl + comp.getSymbol() + "&apikey=" + env.getProperty("av.key");
              CompanyAv compApiData = restTemplate.getForObject(tempUrl, CompanyAv.class);
              CompanyAv company = new CompanyAv
                      (compApiData.getName(), compApiData.getSymbol(), compApiData.getAssetType(), compApiData.getDescription(), compApiData.getAddress());
              allCompData.add(company);
        }

        Collections.sort(allCompData, new StockComparator.SortCompAvBySymbol());
        return allCompData;
    }

    @GetMapping("/feature6")
    // Uses external API call
    /*
        GET all companies name, symbol, and market capitalization (sorted by market cap in descending order)
     */
    public List<CompanyAv> getMarketCap(RestTemplate restTemplate) {
        String ovUrl = AV_URL + "?function=OVERVIEW&symbol=";

        List<CompanyAv> allCompData = new ArrayList<>();

        for (CompanyCsv comp : ALL_CSV_DATA) {
            String tempUrl = ovUrl + comp.getSymbol() + "&apikey=" + env.getProperty("av.key");
            CompanyAv compApiData = restTemplate.getForObject(tempUrl, CompanyAv.class);
            CompanyAv company = new CompanyAv
                    (compApiData.getName(), compApiData.getSymbol(), compApiData.getMarketCap() );
            allCompData.add(company);
        }

        Collections.sort(allCompData, new StockComparator.SortCompAvByMarketCap());
        Collections.reverse(allCompData);
        return allCompData;
    }

}
