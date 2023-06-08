package com.example.factoritecommerce.repository;

import com.example.factoritecommerce.model.UserEcommerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEcommerce, Long> {
    Optional<UserEcommerce> findByDni(String dni);


}
