package com.Fetch.ReceiptProcessor.api.controller;

import com.Fetch.ReceiptProcessor.api.exception.ApiError;
import com.Fetch.ReceiptProcessor.api.exception.CustomReceiptVerificationHandler;
import com.Fetch.ReceiptProcessor.api.model.Receipt;
import com.Fetch.ReceiptProcessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;


    public ReceiptController(ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @PostMapping(
            path = "/process",
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processReceipt(@RequestBody Receipt receipt){
        CustomReceiptVerificationHandler receiptExceptionHandler = new CustomReceiptVerificationHandler();
        ApiError verificationResponse = receiptExceptionHandler.verifyReceipt(receipt);
        Map<String, Object> response = new HashMap<>();

        if(verificationResponse.getStatus() == HttpStatus.OK){
            String uuid = String.valueOf(UUID.randomUUID());
            receipt.setId(uuid);
            receiptService.addReceipt(receipt);
            response.put("id", uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("errors" , verificationResponse.getErrors());
            response.put("message", verificationResponse.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(
            path = "/",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Receipt>> getAllReceipts(){
        List<Receipt> receipts = receiptService.getReceipts();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{id}",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<Receipt> getReceiptById(@PathVariable String id){
        Optional receipt = receiptService.getReceiptById(id);
        if(receipt.isPresent()){
            return new ResponseEntity<>((Receipt) receipt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(
            path = "/{id}/points",
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getReceiptPointsById(@PathVariable String id){
        Optional receipt = receiptService.getReceiptById(id);
        Map<String,Integer> response = new HashMap<>();
        if(receipt.isPresent()){
            Receipt r = (Receipt) receipt.get();
            r.calculatePoints();
            response.put("points", r.getPoints());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
