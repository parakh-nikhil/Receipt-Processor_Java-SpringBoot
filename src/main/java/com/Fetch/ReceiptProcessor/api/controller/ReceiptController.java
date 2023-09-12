package com.Fetch.ReceiptProcessor.api.controller;

import com.Fetch.ReceiptProcessor.api.model.Receipt;
import com.Fetch.ReceiptProcessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Map<String, String>> processReceipt(@RequestBody Receipt receipt){
        String uuid = String.valueOf(UUID.randomUUID());
        Map<String, String> response = new HashMap<>();
        receipt.setId(uuid);
        receiptService.addReceipt(receipt);
        response.put("id", uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
