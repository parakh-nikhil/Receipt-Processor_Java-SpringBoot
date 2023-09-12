package com.Fetch.ReceiptProcessor.api.model;

public class Item {
    private String shortDescription;
    private Double price;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.valueOf(price);
    }
}
