package com.example.factoritecommerce.seeder;

import com.example.factoritecommerce.dto.ProductDto;
import com.example.factoritecommerce.model.Product;
import com.example.factoritecommerce.model.UserEcommerce;
import com.example.factoritecommerce.repository.ProductRepository;
import com.example.factoritecommerce.repository.UserRepository;
import com.example.factoritecommerce.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findAll().isEmpty() && productRepository.findAll().isEmpty()) {
            userRepository.saveAll(List.of(
                    new UserEcommerce(1L, "AndresRod", "andres@gmail.com", "33064279", new ArrayList<>(), false)
            ));
            productRepository.saveAll(List.of(
                    new Product(1l, "Pelota", 15000d, LocalDate.now(), LocalDateTime.now(), new ArrayList<>()),
                    new Product(2l, "Remera", 5000d, LocalDate.now(), LocalDateTime.now(), new ArrayList<>()),
                    new Product(3l, "Bincha", 300d, LocalDate.now(), LocalDateTime.now(), new ArrayList<>()),
                    new Product(4l, "Zapatilla deportiva", 35000d, LocalDate.now(), LocalDateTime.now(), new ArrayList<>()),
                    new Product(5l, "medias", 500d, LocalDate.now(), LocalDateTime.now(), new ArrayList<>())
            ));
        }
    }
}
