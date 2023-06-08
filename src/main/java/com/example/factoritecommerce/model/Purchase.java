package com.example.factoritecommerce.model;
import com.example.factoritecommerce.excepcion.BadRequestException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;
    @NotBlank(message = "Este campo no puede estar en blanco")
    private String numberOfTransaction;
    @CreationTimestamp
    @Column(name = "creationDate", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "userEcommerce_id", referencedColumnName = "id")
    private UserEcommerce userPurchase;
    public Purchase(ShoppingCart shoppingCart, String numberOfTransaction){
        this.shoppingCart= shoppingCart;
        this.numberOfTransaction = numberOfTransaction;
    }
    @PostPersist
    public void UserVipConverter(){
        final Double[] amountPurchase = {0d};
        Long amountPurchaseOnMonth = this.userPurchase.getPurchaseList().stream().filter(purchase ->
                this.date.getMonth().equals(purchase.getDate().getMonth())).count();
        if(amountPurchaseOnMonth>2){
            this.userPurchase.getPurchaseList().stream().forEach(purchase -> {
                if (this.date.getMonth().equals(purchase.getDate().getMonth())) {
                    amountPurchase[0] += purchase.getShoppingCart().getFinalPrice();
                }
            });
        }
        if(amountPurchase[0]>5000d) this.userPurchase.setIsVip(true);
    }
}