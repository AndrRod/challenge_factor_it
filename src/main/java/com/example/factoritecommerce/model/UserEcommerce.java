package com.example.factoritecommerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEcommerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "can't be null or empty")
    private String userName;

    @NotBlank(message = "can't be null or empty")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "can't be null or empty")
    @Column(unique = true)
    private String dni;
    @OneToMany(mappedBy = "userPurchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Purchase> purchaseList;

    private Boolean isVip;

}