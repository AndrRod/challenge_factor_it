package com.example.factoritecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//import javax.validation.constraints.Pattern;
@Data
@Entity
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    // @Pattern(regexp = "([A-Z]{1}[a-zØ-öø-ÿ]{2,})(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?", message = "the name format is incorrect, and remember the names have to start with capital letters") //TODO PROBAR
    private UserEcommerce buyer;
    @Transient
    private Double finalPrice;

    @Enumerated(value = EnumType.STRING)
    private CartType cartType;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "shoping_id_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "shoppingCart_id"))
    public List<Product> items;
    public ShoppingCart(){
        this.finalPrice =  0d;
        this.items = new ArrayList<>();
        this.cartType = CartType.COMMON;
    }
    public Double getFinalPrice() {
        if(items ==null) return 0d;
        items.stream().forEach(orderProduct -> {
            finalPrice += orderProduct.getPrice();});
        setDiscount(finalPrice, finalPrice);
        return finalPrice;
    }
    private void setDiscount(Double priceForDiscount, Double finalPrice){

        //TODO PROBAR promoción es vip
        if(this.items.size()>3){
            Double specialDiscount = this.cartType.name() == CartType.SPECIAL.name() ? 150d : 100d;
            priceForDiscount-=specialDiscount;
        }

        //TODO PROBAR promoción es vip
        if(this.buyer.getIsVip() && finalPrice>2000d) priceForDiscount-=500d;

        //TODO PROBAR promoción 4x3
        for(Product productToSearch: items){
        Long repeatCount = this.items.stream().filter(product ->
            product.equals(productToSearch)).count();
            if(repeatCount>3){
                Integer amountFourProducts = (int) Math.floor(repeatCount/4);
                Double discount = amountFourProducts*productToSearch.getPrice();
                priceForDiscount-=discount;
            }
        }

        this.finalPrice = priceForDiscount;
    }
}