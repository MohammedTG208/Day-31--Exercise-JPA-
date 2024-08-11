package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Service.MerchantStockService;
import com.example.exercisejpa.model.MerchantStock;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchantstock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/getall")
    public ResponseEntity getAllMerchantStock(){
        return merchantStockService.getMerchantStock().isEmpty()?ResponseEntity.status(400).body("The DB is Empty"):ResponseEntity.status(200).body(merchantStockService.getMerchantStock());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(201).body("Merchant Stock Added Successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (merchantStockService.updateMerchantStock(merchantStock,id)){
                return ResponseEntity.status(201).body("Merchant Stock Updated Successfully");
            }
            return ResponseEntity.status(404).body("Merchant Stock Not Found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        if(merchantStockService.deleteMerchantStock(id)){
            return ResponseEntity.status(201).body("Merchant Stock Deleted Successfully");
        }
        return ResponseEntity.status(404).body("Merchant Stock Not Found");
    }

    @PutMapping("/update/stock/{productId}/{merchantStokedID}/{quantity}")
    public ResponseEntity updateStock(@PathVariable Integer productId, @PathVariable Integer merchantStokedID, @PathVariable int quantity){
        if (merchantStockService.updateStock(merchantStokedID,quantity)==1){
            return ResponseEntity.status(400).body("Merchant Stock not found");
        } else{
            return ResponseEntity.status(200).body("Merchant Stock Updated Successfully");
        }
    }
}
