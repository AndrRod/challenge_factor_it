package com.example.factoritecommerce.serviceImpl;

import com.example.factoritecommerce.config.MessageHandler;
import com.example.factoritecommerce.dto.ShoppingCartDto;
import com.example.factoritecommerce.excepcion.MessageResponse;
import com.example.factoritecommerce.excepcion.NotFoundException;
import com.example.factoritecommerce.model.ShoppingCart;
import com.example.factoritecommerce.model.UserEcommerce;
import com.example.factoritecommerce.repository.ShoppingCartRepository;
import com.example.factoritecommerce.service.ShoppingCartService;
import com.example.factoritecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private UserService userService;
    @Override
    public ShoppingCartDto createShoppingCart(String dni, ShoppingCartDto shoppingCartInput) {
        UserEcommerce userEcommerceFound = userService.getUserEntityById(dni);
        shoppingCartInput.setId(null);
        mapper.getConfiguration().setSkipNullEnabled(true);
        ShoppingCart shoppingCart = mapper.map(shoppingCartInput, ShoppingCart.class);
        shoppingCart.setBuyer(userEcommerceFound);
        shoppingCartRepository.save(shoppingCart);
        return mapper.map(shoppingCart, ShoppingCartDto.class);
    }

    @Override
    public ShoppingCartDto updateShoppingCartById(ShoppingCartDto shoppingCartInput, Long id) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(id);
        shoppingCartInput.setId(null);
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(shoppingCartInput, shoppingCart);
        shoppingCartRepository.save(shoppingCart);
        return mapper.map(shoppingCart, ShoppingCartDto.class);
    }

    @Override
    public ShoppingCartDto getShoppingCartById(Long id) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(id);
        return mapper.map(shoppingCart, ShoppingCartDto.class);
    }

    @Override
    public MessageResponse deleteShoppingCartById(Long id) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(id);
        shoppingCartRepository.delete(shoppingCart);
        return new MessageResponse(messageHandler.message("delete", "cart", id.toString()), 200, "/cart");
    }

    @Override
    public List<ShoppingCartDto> getShoppingCarts(Integer page) {
        List<ShoppingCartDto> shoppingCartsDto = shoppingCartRepository.findAll(PageRequest.of(page, 20)).stream()
                .map(shoppingCart -> mapper.map(shoppingCart, ShoppingCartDto.class)).toList();
        return shoppingCartsDto;
    }
    @Override
    public ShoppingCart getShoppingCartEntityById(Long id){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not_found", "cart", id.toString())));
        return shoppingCart;
    }
}
