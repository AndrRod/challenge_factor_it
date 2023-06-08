package com.example.factoritecommerce.model;

import com.example.factoritecommerce.excepcion.BadRequestException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Este campo no puede estar vacio o nulo")
    private String name;
    private Double price;

    @CreationTimestamp
    @Column(name = "creationDate", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate;
    @ManyToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCart> shoppingCarts;
    private Product(){
        this.shoppingCarts = new ArrayList<>();
    }

    @PrePersist
    public void minPrice(){
        if(this.getPrice()<100.00) throw new BadRequestException("no puede tener un precio menor a $100");
    }
}
