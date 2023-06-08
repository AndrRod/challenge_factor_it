package com.example.factoritecommerce.service;

import com.example.factoritecommerce.dto.PurchaseDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PurchaseService {
    PurchaseDto createPurchase(String userDni, Long cartId, PurchaseDto inputPurchase);
    PurchaseDto getPurchaseById(Long id);
    List<PurchaseDto> getPurchases(Integer page);
    List<PurchaseDto> orderByDate(LocalDate date);
    List<PurchaseDto> purchasedByUser(String dni);
    List<PurchaseDto> orderByMountOrDate(String valueOrder);
    Map<String, String> deletePurchaseById(Long id);
    PurchaseDto updatePurchaseById(PurchaseDto inputPurchase, Long id);

}
