package com.fetch.ReceiptProcessor;

public class Item {
    private String shortDescription;
    private int price;

    // A better way is to implement builder design pattern to avoid constructor explosion
    public Item(String shortDescription, int price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
