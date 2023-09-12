package com.Fetch.ReceiptProcessor;

import org.springframework.stereotype.Repository;

@Repository
public class ReceiptDAO {
    private static Receipts listOfReceipts = new Receipts();

    public Receipts getAllReceipts(){
        return listOfReceipts;
    }

    public void addReceipt(Receipt receipt){
        listOfReceipts.addToList(receipt);
    }
}
