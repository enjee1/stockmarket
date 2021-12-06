package com.careerdevs.stockmarket.controllers;

import com.careerdevs.stockmarket.models.CompanyAv;
import com.careerdevs.stockmarket.models.CompanyCsv;
import com.careerdevs.stockmarket.utilties.FeatureFactory;
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

    public String genUrl(String symbol) {
        String url = AV_URL + "?function=OVERVIEW&symbol=" + symbol + "&apikey=" + env.getProperty("av.key");
        return url;
    }


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
            filteredCsvData.add(FeatureFactory.feature1(c));
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
            filteredCsvData.add(FeatureFactory.feature2(c));
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
                filteredCsvData.add(FeatureFactory.feature3And4(c));
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
                filteredCsvData.add(FeatureFactory.feature3And4(c));
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


        List<CompanyAv> allCompData = new ArrayList<>();

        for (CompanyCsv comp : ALL_CSV_DATA) {
              CompanyAv compApiData = restTemplate.getForObject(genUrl(comp.getSymbol()), CompanyAv.class);

              allCompData.add(FeatureFactory.feature5(compApiData));
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

        List<CompanyAv> allCompData = new ArrayList<>();

        for (CompanyCsv comp : ALL_CSV_DATA) {
            CompanyAv compApiData = restTemplate.getForObject(genUrl(comp.getSymbol()), CompanyAv.class);
            allCompData.add(FeatureFactory.feature6(compApiData));
        }

        Collections.sort(allCompData, new StockComparator.SortCompAvByMarketCap());
        Collections.reverse(allCompData);
        return allCompData;
    }

    @GetMapping("/feature7")
    // Uses external API call
    /*
        GET all companies name, symbol and dividend date. Order by which dividend date is the closest to the
        current date. (if the dividend date is Nov 30 and today is Dec 1,
        that would be the furthest a dividend date could be from the current date)
     */
    public List<CompanyAv> getDivDate(RestTemplate restTemplate){
        List<CompanyAv> allCompData = new ArrayList<>();

        for (CompanyCsv comp : ALL_CSV_DATA) {
            CompanyAv compApiData = restTemplate.getForObject(genUrl(comp.getSymbol()), CompanyAv.class);
            allCompData.add(FeatureFactory.feature7(compApiData));
        }

        return allCompData;
    }
}
