package com.fetch.ReceiptProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {
    private int points;
    private String retailer;
    private Date purchaseDate; //TODO change this to Date datatype
    private Date purchaseTime; //TODO change this to Time datatype
    private List<Item> items;
    private int total;

    public Receipt(){
        this.points = 0;
        this.retailer = "";
        this.purchaseDate = new Date();
        this.purchaseTime = new Date();
        this.items = new ArrayList<>();
        this.total = 0;
    }

    // A better way is to implement builder design pattern to avoid constructor explosion
    public Receipt(String retailer, Date purchaseDate, Date purchaseTime, List<Item> items, int total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
        this.calculatePoints();
    }

    public int getPoints() {
        return points;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItemToReceipt(Item item){
        this.items.add(item);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void calculatePoints(){
        int totalPoints = 0;
        //TODO: Calculate points based on the following rules
        /**
         * One point for every alphanumeric character in the retailer name.
         * 50 points if the total is a round dollar amount with no cents.
         * 25 points if the total is a multiple of 0.25.
         * 5 points for every two items on the receipt.
         * If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
         * 6 points if the day in the purchase date is odd.
         * 10 points if the time of purchase is after 2:00pm and before 4:00pm.
         */
        this.points = totalPoints;
    }
}