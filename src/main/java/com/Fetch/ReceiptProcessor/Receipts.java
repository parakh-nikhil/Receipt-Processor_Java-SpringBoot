package com.Fetch.ReceiptProcessor;

import java.util.ArrayList;
import java.util.List;

public class Receipts {
    private List<Receipt> receipts;

    public List<Receipt> getReceipts(){
        return this.receipts.isEmpty() ? new ArrayList<>() : this.receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void addToList(Receipt receipt){
        this.receipts.add(receipt);
    }
}

