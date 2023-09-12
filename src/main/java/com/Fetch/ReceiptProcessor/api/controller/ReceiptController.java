package com.Fetch.ReceiptProcessor.api.controller;

import com.Fetch.ReceiptProcessor.api.model.Receipt;
import com.Fetch.ReceiptProcessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;


    public ReceiptController(ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @GetMapping(path = "/{id}",produces = "application/json")
    @ResponseBody
    public Receipt getReceiptById(@PathVariable Integer id){
        Optional receipt = receiptService.getReceiptById(id);
        if(receipt.isPresent()){
            return (Receipt) receipt.get();
        }
        return null;
    }

    @GetMapping(path = "/",produces = "application/json")
    @ResponseBody
    public List<Receipt> getAllReceipts(){
        List<Receipt> receipts = receiptService.getReceipts();
        return receipts;
    }

    @PostMapping(
            path = "/process",
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseBody
    public int processReceipt(@RequestBody Receipt receipt){
        Integer id = receiptService.getReceipts().size() + 1;
        receipt.setId(id);
        receiptService.addReceipt(receipt);
        return id;
    }

}
