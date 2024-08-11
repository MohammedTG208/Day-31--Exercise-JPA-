package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Service.MerchantService;
import com.example.exercisejpa.model.Merchant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/getall")
    public ResponseEntity getMerchant(){
        return merchantService.getMerchants().isEmpty()?ResponseEntity.status(400).body("The DB is Empty"):ResponseEntity.ok(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            merchantService.addMerchant(merchant);
            return ResponseEntity.status(201).body("Merchant Added successfully");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (merchantService.updateMerchant(merchant,id)){
                return ResponseEntity.status(201).body("Merchant Updated successfully");
            }
            return ResponseEntity.status(404).body("Merchant Not Found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id){
        if (merchantService.removeMerchant(id)){
            return ResponseEntity.status(201).body("Merchant Deleted successfully");
        }else {
            return ResponseEntity.status(404).body("Merchant Not Found");
        }
    }
}
