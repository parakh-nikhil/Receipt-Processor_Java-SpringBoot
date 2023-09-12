package com.Fetch.ReceiptProcessor.api.model;

import com.Fetch.ReceiptProcessor.api.model.Item;
import com.Fetch.ReceiptProcessor.util.PointsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private String id;
    private int points;
//    @Not
    private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private List<Item> items;
    private Double total;

    public int getPoints() {
        return points;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate purchaseDate = LocalDate.parse(purchaseDateStr, formatter);
        this.purchaseDate = purchaseDate;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime purchaseTime = LocalTime.parse(purchaseTimeStr, formatter);
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(String total) { this.total = Double.valueOf(total); }

    public String getId(){ return this.id; }

    public void setId(String id){ this.id = id;}

    public void calculatePoints(){
        this.points =  PointsUtil.calculatePoints(this.retailer, this.total, this.items, this.purchaseDate, this.purchaseTime);
    }
}
