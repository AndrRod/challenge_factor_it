package com.example.factoritecommerce.serviceImpl;

import com.example.factoritecommerce.dto.PurchaseDto;
import com.example.factoritecommerce.excepcion.NotFoundException;
import com.example.factoritecommerce.model.Purchase;
import com.example.factoritecommerce.model.ShoppingCart;
import com.example.factoritecommerce.model.UserEcommerce;
import com.example.factoritecommerce.repository.PurchaseRepository;
import com.example.factoritecommerce.service.PurchaseService;
import com.example.factoritecommerce.service.ShoppingCartService;
import com.example.factoritecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Override
    public PurchaseDto createPurchase(String userDni, Long id, PurchaseDto inputPurchase) {
        UserEcommerce userEcommerce = userService.getUserEntityById(userDni);
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartEntityById(id);
        inputPurchase.setId(null);
        mapper.getConfiguration().setSkipNullEnabled(true);
        Purchase purchase = mapper.map(inputPurchase, Purchase.class);
        purchase.setUserPurchase(userEcommerce);
        purchase.setShoppingCart(shoppingCart);
        purchaseRepository.save(purchase);
        return mapper.map(purchase, PurchaseDto.class);
    }

    @Override
    public PurchaseDto getPurchaseById(Long id) {
        Purchase purchase = getPurchaseEntityById(id);
        return mapper.map(purchase, PurchaseDto.class);
    }

    @Override
    public List<PurchaseDto> getPurchases(Integer page) {
        List<PurchaseDto> purchaseDtos = purchaseRepository.findAll(PageRequest.of(page, 20)).stream()
                .map(purchase -> mapper.map(purchase, PurchaseDto.class)).toList();
        return purchaseDtos;
    }

    @Override
    public List<PurchaseDto> orderByDate(LocalDate date) {
        List<PurchaseDto> purchaseDtoList = purchaseRepository.orderByDateAsc().stream()
                .map(purchase -> mapper.map(purchase, PurchaseDto.class)).toList();
        return purchaseDtoList;
    }

    @Override
    public List<PurchaseDto> purchasedByUser(String dni) {
        List<PurchaseDto> purchaseList = purchaseRepository.findByuserPurchaseDni(dni).stream()
                .map(purchase -> mapper.map(purchase, PurchaseDto.class)).toList();
        return purchaseList;
    }

    @Override
    public List<PurchaseDto> orderByMountOrDate(String valueOrder) {
        return null;
    }

    @Override
    public Map<String, String> deletePurchaseById(Long id) {
        Purchase purchaseFound = getPurchaseEntityById(id);
        purchaseRepository.delete(purchaseFound);
        return Map.of("Message", "El purchse con id " + id + " fue eliminado correctamente");
    }

    @Override
    public PurchaseDto updatePurchaseById(PurchaseDto inputPurchase, Long id) {
        Purchase purchaseFound = getPurchaseEntityById(id);
        inputPurchase.setId(null);
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(inputPurchase, purchaseFound);
        purchaseRepository.save(purchaseFound);
        return mapper.map(purchaseFound, PurchaseDto.class);
    }
    private Purchase getPurchaseEntityById(Long id){
        Purchase purchaseFound = purchaseRepository.findById(id).orElseThrow(()-> new NotFoundException("Compra no encontrada"));
        return purchaseFound;
    }
}
