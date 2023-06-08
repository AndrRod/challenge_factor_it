package com.example.factoritecommerce.controller;

import com.example.factoritecommerce.dto.UserDto;
import com.example.factoritecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{dni}")
    public ResponseEntity<UserDto> findById(@PathVariable String dni){
        return ResponseEntity.ok(userService.getUserByDni(dni));
    }
    @GetMapping
    public  ResponseEntity<List<UserDto>> getListDto(@RequestParam(required = false) Integer page){
        return ResponseEntity.ok(userService.getAllUser(page));
    }
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userInput){
        return ResponseEntity.status(201).body(userService.createUser(userInput));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String dni){
        return ResponseEntity.ok(userService.deleteUser(dni));
    }
    @PutMapping("/{dni}")
    public ResponseEntity<UserDto> update(@PathVariable String dni, @RequestBody UserDto userInput){
        return ResponseEntity.ok(userService.updateUser(userInput, dni));
    }
}
