package com.example.factoritecommerce.dto;

import com.example.factoritecommerce.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCartDto {
    private Long id;
    private String buyerName;
    private String buyerEmail;
    private String buyerDni;
    private Double finalPrice;
    private List<Product> items;
    private Boolean showEntity;
    private Boolean isSpecial;

}
