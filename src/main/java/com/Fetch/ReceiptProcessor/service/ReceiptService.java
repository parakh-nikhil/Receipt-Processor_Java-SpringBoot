package com.Fetch.ReceiptProcessor.service;

import com.Fetch.ReceiptProcessor.api.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceiptService {
    private Map<String, Receipt> receiptIdMap;

    public ReceiptService() {
        this.receiptIdMap = new HashMap<>();
    }

    public List<Receipt> getReceipts(){
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.addAll(this.receiptIdMap.values());
        return receiptList;
    }

    public Optional<Receipt> getReceiptById(String id) {
        Optional optional = Optional.empty();
        if(this.receiptIdMap.containsKey(id)){
            optional = Optional.of(this.receiptIdMap.get(id));
        }
        return optional;
    }

    public void addReceipt(Receipt receipt){
        this.receiptIdMap.put(receipt.getId(), receipt);
    }

}
