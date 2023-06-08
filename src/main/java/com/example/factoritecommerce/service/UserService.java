package com.example.factoritecommerce.service;

import com.example.factoritecommerce.dto.UserDto;
import com.example.factoritecommerce.model.UserEcommerce;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface UserService {
    UserDto createUser(UserDto userInput);
    UserDto updateUser(UserDto userInput, String dni);
    Map<String, String> deleteUser(String dni);
    List<UserDto> getAllUser(Integer page);
    UserDto getUserByDni(String dni);
    UserEcommerce getUserEntityById(String dni);
}
