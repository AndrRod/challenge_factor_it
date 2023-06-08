package com.example.factoritecommerce.repository;

import com.example.factoritecommerce.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT p FROM Purchase p ORDER BY p.date ASC")
    List<Purchase> orderByDateAsc();

    List<Purchase> findByuserPurchaseDni(String dni);
}
