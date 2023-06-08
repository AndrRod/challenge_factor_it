package com.example.factoritecommerce.controller;

import com.example.factoritecommerce.dto.PurchaseDto;
import com.example.factoritecommerce.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;


    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }
    @GetMapping("/user/{dni}")
    public ResponseEntity<List<PurchaseDto>> findById(@PathVariable String dni){
        return ResponseEntity.ok(purchaseService.purchasedByUser(dni));
    }

    @GetMapping
    public  ResponseEntity<List<PurchaseDto>> getListDto(@RequestParam Integer page){
        return ResponseEntity.ok(purchaseService.getPurchases(page));
    }
    @PostMapping("/dni/{dni}/cart/{idCart}")
    public ResponseEntity<PurchaseDto> create(@PathVariable String dni, @PathVariable Long idCart, @RequestBody @Valid PurchaseDto purchaseInput){
        return ResponseEntity.status(201).body(purchaseService.createPurchase(dni, idCart, purchaseInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(purchaseService.deletePurchaseById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDto> update(@PathVariable Long id, @RequestBody PurchaseDto purchaseInput){
        return ResponseEntity.ok(purchaseService.updatePurchaseById(purchaseInput, id));
    }

}
