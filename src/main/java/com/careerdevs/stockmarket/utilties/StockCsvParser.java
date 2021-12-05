package com.careerdevs.stockmarket.utilties;

import com.careerdevs.stockmarket.models.CompanyCsv;
import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockCsvParser {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(readCSV());
    }

    public static List<CompanyCsv> readCSV() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/data_set_1.csv"), ',');

            List<CompanyCsv> allCompanies = new ArrayList<CompanyCsv>();

            // read line by line
            String[] record = null;

            while ((record = reader.readNext()) != null) {
                //symbol,name,exchange,assetType,ipoDate,delistingDate,status
                CompanyCsv company = new CompanyCsv();
                company.setSymbol(record[0]);
                company.setName(record[1]);
                company.setExchange(record[2]);
                company.setAssetType(record[3]);
                company.setIpoDate(record[4]);
                company.setDelistingDate(record[5]);
                company.setStatus(record[6]);
                allCompanies.add(company);
            }

            reader.close();

            return allCompanies;
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            System.out.println("ERROR READING DATA");
        }

        return null;
    }

}
