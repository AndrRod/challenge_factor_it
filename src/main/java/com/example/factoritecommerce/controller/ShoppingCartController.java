package com.example.factoritecommerce.controller;

import com.example.factoritecommerce.dto.ShoppingCartDto;
import com.example.factoritecommerce.excepcion.MessageResponse;
import com.example.factoritecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.getShoppingCartById(id));
    }
    @GetMapping
    public  ResponseEntity<List<ShoppingCartDto>> getListDto(@RequestParam Integer page){
        return ResponseEntity.ok(shoppingCartService.getShoppingCarts(page));
    }
    @PostMapping("/dni/{userDni}")
    public ResponseEntity<ShoppingCartDto> create(@PathVariable String userDni, @RequestBody @Valid ShoppingCartDto shoppingCartDto){
        return ResponseEntity.status(201).body(shoppingCartService.createShoppingCart(userDni, shoppingCartDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.deleteShoppingCartById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> update(@PathVariable Long id, @RequestBody ShoppingCartDto shoppingCartDto){
        return ResponseEntity.ok(shoppingCartService.updateShoppingCartById(shoppingCartDto, id));
    }
}
