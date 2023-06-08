package com.example.factoritecommerce.dto;

import com.example.factoritecommerce.model.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseDto {
    private Long id;
    private ShoppingCart shoppingCart;
    private String numberOfTransaction;

}
