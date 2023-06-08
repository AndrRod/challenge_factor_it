package com.example.factoritecommerce.controller;

import com.example.factoritecommerce.dto.ProductDto;
import com.example.factoritecommerce.dto.UserDto;
import com.example.factoritecommerce.service.ProductService;
import com.example.factoritecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping
    public  ResponseEntity<List<ProductDto>> getListDto(@RequestParam Integer page){
        return ResponseEntity.ok(productService.getAllProducts(page));
    }
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto productInput){
        return ResponseEntity.status(201).body(productService.createProduct(productInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productInput){
        return ResponseEntity.ok(productService.updateProductById(id, productInput));
    }
}
