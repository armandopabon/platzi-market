package com.tutorial.platzimarket.web.controller;

import com.tutorial.platzimarket.domain.Purchase;
import com.tutorial.platzimarket.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/All")
    public ResponseEntity<List<Purchase>> getAll(){
       return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getByClient/{id}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String idCliente) {
        return ResponseEntity.of(purchaseService.getByClient(idCliente));
    }

    @PostMapping("/Save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>( purchaseService.save(purchase), HttpStatus.OK);
    }
}
