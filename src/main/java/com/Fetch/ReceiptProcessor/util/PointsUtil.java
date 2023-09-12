package com.Fetch.ReceiptProcessor.util;

import com.Fetch.ReceiptProcessor.api.model.Item;

import java.util.List;

public class PointsUtil {
    public static int calculatePoints(String retailerName, Double totalAmt, List<Item> itemsList, String date, String time){
        return calculatePointsByNameAlphaNumeric(retailerName) +
                calculatePointsByTotalAmount(totalAmt) +
                calculatePointsByItemsList(itemsList) +
                calculatePointsByPurchaseDate(date) +
                calculatePointsByPurchaseTime(time);

    }

    public static int calculatePointsByNameAlphaNumeric(String name){
        int points = 0;
        for(int i=0 ; i<name.length() ; i++){
            if (Character.isLetterOrDigit(name.charAt(i))) {
                points++;
            }
        }
        return points;
    }

    public static int calculatePointsByTotalAmount(Double total){
        int points = 0;
        Double cents = total%1;
        if(cents == 0.0){
            points += 50;
        }
        if(cents %0.25 == 0){
            points += 25;
        }
        return points;
    }

    public static int calculatePointsByItemsList(List<Item> items){
        int points = 0;
        points += (items.size() / 2) * 5;
        for(Item item : items){
            if(item.getShortDescription().trim().length()%3==0){
                points += (int) Math.ceil(item.getPrice() * 0.2);
            }
        }
        return points;
    }

    public static int calculatePointsByPurchaseDate(String purchaseDate){
        int points = 0;
        String[] date = purchaseDate.split("-");
        if(Integer.parseInt(date[2]) % 2 != 0){
            points += 6;
        }
        return points;
    }

    public static int calculatePointsByPurchaseTime(String purchaseTime){
        int points = 0;
        String[] time = purchaseTime.split(":");
        if(Integer.parseInt(time[0]) >= 14 && Integer.parseInt(time[0]) < 16){
            points += 10;
        }
        return points;
    }
}
