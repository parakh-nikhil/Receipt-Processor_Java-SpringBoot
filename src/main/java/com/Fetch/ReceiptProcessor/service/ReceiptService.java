package com.Fetch.ReceiptProcessor.service;

import com.Fetch.ReceiptProcessor.api.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {
    private List<Receipt> receiptList;

    public ReceiptService() {
        this.receiptList = new ArrayList<>();
    }

    public List<Receipt> getReceipts(){
        return this.receiptList;
    }

    public Optional<Receipt> getReceiptById(Integer id) {
        Optional optional = Optional.empty();
        for(Receipt r : this.receiptList){
            if(r.getId() == id){
                optional = Optional.of(r);
                return optional;
            }
        }
        return optional;
    }

    public void addReceipt(Receipt receipt){
        this.receiptList.add(receipt);
    }

}
