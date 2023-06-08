package com.example.factoritecommerce.service;

import com.example.factoritecommerce.dto.ShoppingCartDto;
import com.example.factoritecommerce.excepcion.MessageResponse;
import com.example.factoritecommerce.model.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
    ShoppingCartDto createShoppingCart(String dniUser, ShoppingCartDto shoppingCartInput);
    ShoppingCartDto updateShoppingCartById(ShoppingCartDto shoppingCartInput, Long id);
    ShoppingCartDto getShoppingCartById(Long id);
    MessageResponse deleteShoppingCartById(Long id);
    List<ShoppingCartDto> getShoppingCarts(Integer page);
    ShoppingCart getShoppingCartEntityById(Long id);

}
