package com.Fetch.ReceiptProcessor.api.exception;

import com.Fetch.ReceiptProcessor.api.model.Item;
import com.Fetch.ReceiptProcessor.api.model.Receipt;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomReceiptVerificationHandler {
    public List<String> errors = new ArrayList<>();

    public ApiError verifyReceipt(Receipt receipt){
        Boolean verifyStatus;
        verifyStatus = this.verifyRetailerName(receipt.getRetailer());
        verifyStatus &= this.verifyPurchaseDate(receipt.getPurchaseDate());
        verifyStatus &= this.verifyPurchaseTime(receipt.getPurchaseTime());
        verifyStatus &= this.verifyItemsList(receipt.getItems());
        verifyStatus &= this.verifyTotal(receipt.getTotal(), receipt.getItems());
        HttpStatus status = (verifyStatus) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        String message = (verifyStatus) ? "Receipt Ok" : "Receipt Object not formatted correctly!";
        return new ApiError(status, message, errors);
    }

    public Boolean verifyRetailerName(String retailerName){
        if(retailerName.isEmpty()){
            this.errors.add("Retailer name cannot be blank or null");
            return false;
        }
        return true;
    }

    public Boolean verifyPurchaseDate(String purchaseDateStr){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate purchaseDate = LocalDate.parse(purchaseDateStr, formatter);
            if (!LocalDate.now().isAfter(purchaseDate)){
                errors.add("Purchase date must be in the past");
                return false;
            }
        }catch(Exception e){
            errors.add(e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean verifyPurchaseTime (String purchaseTimeStr){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime purchaseTime = LocalTime.parse(purchaseTimeStr, formatter);
            if (!LocalTime.now().isAfter(purchaseTime)){
                errors.add("Purchase time must be in the past");
                return false;
            }
        }catch(Exception e){
            errors.add(e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean verifyItemsList(List<Item> itemList){
        if(itemList.size() == 0){
            this.errors.add("No items in the receipt");
            return false;
        }
        return true;
    }

    public Boolean verifyTotal(Double total, List<Item> itemList){
        if(total < 0){
            errors.add("Total amount cannot be less than 0");
            return false;
        }
        Double itemTotal = 0.0;
        for (Item item : itemList){
            itemTotal += item.getPrice();
        }
        if(itemTotal != total){
            errors.add("Total is not equal to the sum of individual item prices");
            return false;
        }
        return true;
    }
}
