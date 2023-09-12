package com.Fetch.ReceiptProcessor.api.model;

import com.Fetch.ReceiptProcessor.api.model.Item;
import com.Fetch.ReceiptProcessor.util.PointsUtil;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {
    private String id;
    private int points;
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
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
