package com.careerdevs.stockmarket.utilties;

import com.careerdevs.stockmarket.models.CompanyAv;
import com.careerdevs.stockmarket.models.CompanyCsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class StockComparator {
    public static class SortCompCsvBySymbol implements Comparator<CompanyCsv> {
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

    public static class SortCompCsvByIpoDate implements Comparator<CompanyCsv> {
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

    public static class SortCompAvBySymbol implements Comparator<CompanyAv> {
        public int compare(CompanyAv a, CompanyAv b) {
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

    public static class SortCompAvByMarketCap implements Comparator<CompanyAv> {

        public int compare(CompanyAv a, CompanyAv b) {
            int result;

            String compAMkCp = a.getMarketCap();
            String compBMkCp = b.getMarketCap();

            if (compAMkCp == null && compBMkCp != null) {
                result = 1;
            } else if (compAMkCp != null && compBMkCp == null) {
                result = -1;
            } else if (compAMkCp == null){
                result = 0;
            } else {
                long compAMc = Long.parseLong(a.getMarketCap());
                long compBMc = Long.parseLong(b.getMarketCap());
                result = Long.compare(compAMc, compBMc);
            }

            return result;
        }
    }

    public static class SortCompAvByDivDate implements Comparator<CompanyAv> {

        public int compare(CompanyAv a, CompanyAv b) {
            int result;
            String dateString1 = a.getDivDate();
            String dateString2 = b.getDivDate();
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
